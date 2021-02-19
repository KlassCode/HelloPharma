package com.klasscode.helloPharma.model;

import java.sql.Connection;
import java.util.ArrayList;

import com.klasscode.helloPharma.mode.connection.DbConnection;

public abstract class DAO<T> {
	
protected Connection connection;
	
	public DAO() {
		this.connection = DbConnection.getInstance();
	}
	
	public abstract boolean create(T objet);
	
	public abstract boolean delete(T objet);
	
	public abstract boolean update(T objet);
	
	public abstract T search(int id);
	
	public abstract ArrayList<T>  selectAll();
	
}
