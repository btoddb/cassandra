
package org.apache.cassandra.notify;

import org.apache.cassandra.db.ColumnFamily;
import org.apache.cassandra.db.DecoratedKey;

public class MutationEvent
{
	private final String keyspaceName;
	private final String columnFamilyName;
	@SuppressWarnings("rawtypes")
	private final DecoratedKey key;
	private final ColumnFamily columnFamily;
	
	public MutationEvent(String keyspaceName, String columnFamilyName, @SuppressWarnings("rawtypes") DecoratedKey key, ColumnFamily columnFamily)
	{
		this.keyspaceName = keyspaceName;
		this.columnFamilyName = columnFamilyName;
		this.key = key;
		this.columnFamily = columnFamily;
	}

	@SuppressWarnings("rawtypes")
	public DecoratedKey getKey()
	{
		return key;
	}

	public ColumnFamily getColumnFamily()
	{
		return columnFamily;
	}

	public String getKeyspaceName()
	{
		return keyspaceName;
	}

	public String getColumnFamilyName()
	{
		return columnFamilyName;
	}

}
