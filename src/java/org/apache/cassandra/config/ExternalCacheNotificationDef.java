
package org.apache.cassandra.config;


public class ExternalCacheNotificationDef
{

	public String listener_class = null;
	public boolean notify_on_local_mutate = false;
	public boolean notify_on_replica_mutate = false;
	public boolean notify_on_dc_mutate = true;
	public boolean notify_on_repairs = true;
	public boolean notify_on_hints = true;
	public boolean notify_on_commitlog_replay = true;

}
