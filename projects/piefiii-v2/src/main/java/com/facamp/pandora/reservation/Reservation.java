package com.facamp.pandora.reservation;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.facamp.pandora.user.User;

@Entity
public class Reservation {

	@Id
	private Integer id;
	private String reservationDate;
	private int queuePosition;
	//Private Collection<Glassware> glasswareCollection;
	//Private Collection<Chemical> chemicalCollection;
	
	@OneToOne
	private User user;
	
	public Reservation() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public int getQueuePosition() {
		return queuePosition;
	}

	public void setQueuePosition(int queuePosition) {
		this.queuePosition = queuePosition;
	}

	
	
}
