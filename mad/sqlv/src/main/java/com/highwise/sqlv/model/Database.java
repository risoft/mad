package com.highwise.sqlv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="_database")
public class Database  {

	
	@Id @GeneratedValue
	private long id;
	@Column(nullable=false, length=50)
	private String name;
	@Column(nullable=false, length=50)
	private String username;
	@Column(nullable=false, length=50)
	private String password;
	@Column(nullable=false, length=50)
	private String url;
	
	
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	
	
}
