package com.highwise.sqlv.webhelp;

import java.util.List;

public class ResultSetDto {
	
	private List<String> columns;
	private List<List<Object>> rows;
	private long total;
	private boolean success = true;
	
	public ResultSetDto(List<String> columns, List<List<Object>> rows, long total) {
		this.columns = columns;
		this.rows = rows;
		this.total = total;
	}

	
	public List<String> getColumns() {
		return columns;
	}
	public void setColumns(List<String> columns) {
		this.columns = columns;
	}
	public List<List<Object>> getRows() {
		return rows;
	}
	public void setRows(List<List<Object>> rows) {
		this.rows = rows;
	}
	
	public long getTotal() {
		return total;
	}
	
	public void setTotal(long total) {
		this.total = total;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	public String getTemplate() {
		return 
				columns == null ? null :
					(
						columns.size() == 2 ? 
						"{"+columns.get(0)+"}: {"+columns.get(1)+"}" 
						: "{"+columns.get(0)+"}"
					);
	}
}
