package com.facamp.pandora.user;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	private int id;
	private String name;
	private String login;
	private String password;
	private Integer permission;
	
	//private Collection<Reservation> reservationCollection;
	
	
	public int getId() {
		return id;
	}

	public User() {
		super();
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPermission() {
		return permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

	/*public Collection<Reservation> getReservationCollection() {
		return reservationCollection;
	}

	public void setReservationCollection(Collection<Reservation> reservationCollection) {
		this.reservationCollection = reservationCollection;
	}*/
	
	
}
