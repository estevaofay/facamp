package com.facamp.pandora.glassware;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Glassware {
	@Id
	private Integer id;
	private String name;
	private String photo;
	private String type;
	private int quantity;
//private Collection<Reservation> reservationCollection;
	private int capacity;

	
	public Glassware() {
	super();
	}
	public Integer getId() {
		return id;
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
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
}
