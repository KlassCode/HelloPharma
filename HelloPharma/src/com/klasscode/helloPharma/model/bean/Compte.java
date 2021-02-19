package com.klasscode.helloPharma.model.bean;

import java.util.Date;

public class Compte {
	
	private int id;
	private String username;
	private String password;
	private String privileges;
	private Date dateCreation;
	private int activate;
	
	public Compte() {}
	
	public Compte(int id, String username, String password, String privileges, Date dateCreation, int activate) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.privileges = privileges;
		this.dateCreation = dateCreation;
		this.activate = activate;
	}

	public Compte(String username, String password, String privileges, Date dateCreation, int activate) {
		super();
		this.username = username;
		this.password = password;
		this.privileges = privileges;
		this.dateCreation = dateCreation;
		this.activate = activate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPrivileges() {
		return privileges;
	}

	public void setPrivileges(String privileges) {
		this.privileges = privileges;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public int getActivate() {
		return activate;
	}

	public void setActivate(int activate) {
		this.activate = activate;
	}
	
}
