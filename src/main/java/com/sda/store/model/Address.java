package com.sda.store.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAddress;
	private String country;
	private String city;
	private String streeet;
	private String zip;

	@OneToOne
	@JoinColumn(name = "idUser")
	private User user;

	@OneToOne
	@JoinColumn(name = "idOrder")
	private Order order;

	public int getIdAddress() {
		return idAddress;
	}

	public void setIdAddress(int idAddress) {
		this.idAddress = idAddress;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreeet() {
		return streeet;
	}

	public void setStreeet(String streeet) {
		this.streeet = streeet;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Address [idAddress=" + idAddress + ", country=" + country + ", city=" + city + ", streeet=" + streeet
				+ ", zip=" + zip + "]";
	}
}
