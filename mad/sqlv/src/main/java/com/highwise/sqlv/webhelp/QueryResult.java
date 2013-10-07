package com.highwise.sqlv.webhelp;

import java.util.List;

public class QueryResult {
	
	private List rows;
	private boolean success = true;
	

	public QueryResult(List rows) {
		this.rows = rows;
	}



	public List getRows() {
		return rows;
	}
	
	public boolean isSuccess() {
		return success;
	}

}
