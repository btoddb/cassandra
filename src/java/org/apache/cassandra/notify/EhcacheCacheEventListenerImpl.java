
package org.apache.cassandra.notify;

import java.nio.ByteBuffer;
import java.util.List;

import org.apache.cassandra.db.IColumn;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EhcacheCacheEventListenerImpl implements ExternalCacheEventListener
{
	private static Logger logger = LoggerFactory.getLogger(EhcacheCacheEventListenerImpl.class);
	private HttpClient httpClient;

	private String host = "localhost";
	private int port = 8080;
	private String path = "/ehcache/rest/";

//	public EhcacheCacheEventListenerImpl()
//	{
//		restSetup();
//	}

	@Override
	public void start()
	{
		MultiThreadedHttpConnectionManager connMgr = new MultiThreadedHttpConnectionManager();
		HttpConnectionManagerParams connMgrParms = new HttpConnectionManagerParams();
		connMgrParms.setDefaultMaxConnectionsPerHost(10);
		connMgrParms.setMaxTotalConnections(50);
		connMgrParms.setTcpNoDelay(true);
		connMgr.setParams(connMgrParms);
		httpClient = new HttpClient(connMgr);
	}

	@Override
	public void mutationNotification(List<MutationEvent> eventList)
	{
		if (null == eventList || eventList.isEmpty())
		{
			return;
		}

		for (MutationEvent event : eventList)
		{
			String cfName = event.getColumnFamily().getName();
			String key = new String( event.getKey().key.array());
			String restCallBase = "http://" + host + ":" + port + path + cfName
					+ "/";
			for (IColumn col : event.getColumnFamily())
			{
				String colName = new String(col.name().array());
				String colVal = new String(col.value().array());
				String restCall = restCallBase +key+":"+colName;
				
				logger.debug("Mutation : colFam="+cfName + ", key=" + key + ", colName="+colName + ", colVal="+colVal);
				PutMethod put = new PutMethod(restCall);
				put.setRequestEntity(new StringRequestEntity(colVal));
				try
				{
					httpClient.executeMethod(put);
				}
				catch (Throwable e)
				{
					logger.error("exception while ehcache'ing the data", e);
				}
			}
		}
	}

	private String keyToStr(ByteBuffer key)
	{
		ByteBuffer bb = key.duplicate();
		StringBuffer sb = new StringBuffer();
		while (bb.hasRemaining())
		{
			sb.append((char) bb.get());
		}
		return sb.toString();
	}
	
	@Override
	public void shutdown() {
		
	}
}
