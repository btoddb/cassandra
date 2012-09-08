package org.apache.cassandra.notify;

import java.util.List;


public interface ExternalCacheEventListener
{

	void mutationNotification(List<MutationEvent> eventList);
	
	void start();
	void shutdown();
}
