package com.highwise.sqlv.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="persistent_logins")
public class PersistentLogins  {
   

	
    @OneToOne
    @JoinColumn(nullable=false, name="username")
    private User username;
    
	@Id
	@Column(length=64)
	private String series;

	@Column(length=64, nullable=false)
	private String token;

	@Column(name="last_used", nullable=false)
	private Date lastUsed;

	public User getUsername() {
		return username;
	}

	public void setUsername(User username) {
		this.username = username;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getLastUsed() {
		return lastUsed;
	}

	public void setLastUsed(Date lastUsed) {
		this.lastUsed = lastUsed;
	}

	
	
}
