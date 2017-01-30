package com.tda.forge.team;

public class ProjectDAO {

	String id;

	String password;

	String firstName;

	String lastName;

	public ProjectDAO(String id) {
		super();
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String toString() {
		return "ProjectDAO [id: " + id + ", password=*************]";
	}
}
