package com.vano.clientserver;

import java.io.Serializable;


public class Table implements Serializable {

	private long id;
	private String table;

	public Table(String table)
	{
		this.id=Incrementator.id++;
		this.table=table;
	}

	
	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}




}
