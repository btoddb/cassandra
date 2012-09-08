package org.apache.cassandra.notify;

public class ExternalCacheEventDetails
{
	private ExternalCacheEventListener listener;
	private boolean notifyOnLocalMutation = false;
	private boolean notifyOnReplicaMutation = false;
	private boolean notifyOnDcMutation = true;
	private boolean notifyOnRepair = true;
	private boolean notifyOnHint = true;
	private boolean notifyOnCommitlogReplay = true;
	
	public ExternalCacheEventDetails(ExternalCacheEventListener listener, boolean notifyOnLocal,
			boolean notifyOnReplica, boolean notifyOnDc, boolean notifyOnRepair, boolean notifyOnHint, boolean notifyOnCommitlogReplay)
	{
		this.listener = listener;
		this.notifyOnLocalMutation = notifyOnLocal;
		this.notifyOnReplicaMutation = notifyOnReplica;
		this.notifyOnDcMutation = notifyOnDc;
		this.notifyOnRepair = notifyOnRepair;
		this.notifyOnHint = notifyOnHint;
		this.notifyOnCommitlogReplay = notifyOnCommitlogReplay;
	}

	public ExternalCacheEventListener getListener()
	{
		return listener;
	}

	public void setListener(ExternalCacheEventListener listener)
	{
		this.listener = listener;
	}

	public void setNotifyOnLocalMutation(boolean notifyOnLocal)
	{
		this.notifyOnLocalMutation = notifyOnLocal;
	}

	public void setNotifyOnReplicaMutation(boolean notifyOnReplicaMutation)
	{
		this.notifyOnReplicaMutation = notifyOnReplicaMutation;
	}

	public void setNotifyOnDcMutation(boolean notifyOnDcMutation)
	{
		this.notifyOnDcMutation = notifyOnDcMutation;
	}

	/**
	 * Considers if a listener is set in addition to the state of 'local notify'
	 * 
	 * @return
	 */
	public boolean notifyOnLocalMutation()
	{
		return notifyOnLocalMutation && null != listener; 
	}

	/**
	 * Considers if a listener is set in addition to the state of 'replica notify'
	 * 
	 * @return
	 */
	public boolean notifyOnReplicaMutation()
	{
		return notifyOnReplicaMutation && null != listener; 
	}

	/**
	 * Considers if a listener is set in addition to the state of 'DC notify'
	 * 
	 * @return
	 */
	public boolean notifyOnDcMutation()
	{
		return notifyOnDcMutation && null != listener; 
	}

	/**
	 * Considers if a listener is set in addition to the state of 'repair notify'
	 * 
	 * @return
	 */
	public boolean notifyOnRepair()
	{
		return notifyOnRepair && null != listener;
	}

	/**
	 * Considers if a listener is set in addition to the state of 'hint notify'
	 * 
	 * @return
	 */
	public boolean notifyOnHint()
	{
		return notifyOnHint && null != listener;
	}

	/**
	 * Considers if a listener is set in addition to the state of 'commitlog replay notify'
	 * 
	 * @return
	 */
	public boolean notifyOnCommitlogReplay()
	{
		return notifyOnCommitlogReplay && null != listener;
	}
	
}
