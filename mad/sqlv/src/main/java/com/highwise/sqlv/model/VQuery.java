package com.highwise.sqlv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class VQuery {

	
	@Id @GeneratedValue
	private long id;
	@Column(nullable=false, length=50)
	private String name;
	@Column(nullable=false)
	private String query;
	@ManyToOne
	private Database connection;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	
	public void setConnection(Database connection) {
		this.connection = connection;
	}
	
	public Database getConnection() {
		return connection;
	}
	
	
	
}
