package com.facamp.pandora.chemical;


import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Chemical {

	@Id
	private Integer id;
	private String name;
	private String photo;
	private String type;
	private int quantity;
	//private Collection<Reservation> reservationCollection;
	
	private String formula;
	private String unit;
	private String expirationDate;
	private String safetySheet;
	
	public Chemical(){
		
	}
	
	public Chemical(Integer id, String name, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
	}
	
	
	
	public Chemical(Integer id, String name, String photo, String type, int quantity, String formula, String unit,
			String expirationDate, String safetySheet) {
		super();
		this.id = id;
		this.name = name;
		this.photo = photo;
		this.type = type;
		this.quantity = quantity;
		this.formula = formula;
		this.unit = unit;
		this.expirationDate = expirationDate;
		this.safetySheet = safetySheet;
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
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getSafetySheet() {
		return safetySheet;
	}
	public void setSafetySheet(String safetySheet) {
		this.safetySheet = safetySheet;
	}
	
	
	
}
