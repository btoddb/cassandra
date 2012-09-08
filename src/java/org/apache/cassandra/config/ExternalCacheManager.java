
package org.apache.cassandra.config;

import org.apache.cassandra.notify.ExternalCacheEventDetails;
import org.apache.cassandra.notify.ExternalCacheEventListener;
import org.apache.cassandra.utils.FBUtilities;

public class ExternalCacheManager
{
	public static ExternalCacheEventDetails externalCacheEventDetails;

	public static void init(String listener_class, boolean notify_on_local_mutate,
			boolean notify_on_replica_mutate, boolean notify_on_dc_mutate,
			boolean notify_on_repairs, boolean notify_on_hints, boolean notify_on_commitlog_replay)
			throws ConfigurationException
	{
		if ( null != externalCacheEventDetails ) {
			return;
		}
		
		init((ExternalCacheEventListener) (null != listener_class ? FBUtilities.construct(
				listener_class, "external-cache-listener") : null), notify_on_local_mutate,
				notify_on_replica_mutate, notify_on_dc_mutate, notify_on_repairs, notify_on_hints,
				notify_on_commitlog_replay);
		if (null != externalCacheEventDetails.getListener())
		{
			externalCacheEventDetails.getListener().start();
		}

	}

	public static void init(ExternalCacheEventListener listener, boolean notify_on_local_mutate,
			boolean notify_on_replica_mutate, boolean notify_on_dc_mutate,
			boolean notify_on_repairs, boolean notify_on_hints, boolean notify_on_commitlog_replay)
			throws ConfigurationException
	{
		if ( null != externalCacheEventDetails ) {
			return;
		}
		
		externalCacheEventDetails = new ExternalCacheEventDetails(listener, notify_on_local_mutate,
				notify_on_replica_mutate, notify_on_dc_mutate, notify_on_repairs, notify_on_hints,
				notify_on_commitlog_replay);
	}

	public static ExternalCacheEventDetails getExternalCacheEventDetails()
	{
		return externalCacheEventDetails;
	}

}
