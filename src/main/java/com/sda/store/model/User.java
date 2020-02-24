package com.sda.store.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class User {

	@Id
	@Column(length = 45)
	private String username;
	private String password;
	@NotBlank
	@Size(min = 2, max = 30)
	private String firstName;
	@NotBlank
	@Size(min = 2, max = 30)
	private String lastName;
	@NotBlank
	private String login;
	@Column(columnDefinition = "varchar(45) default 'default'")
	@NotBlank
	private String logo;
	private boolean admin;

	@OneToOne
	@JoinColumn(name = "idAddress")
	private Address address;

	@OneToMany(mappedBy = "user")
	private List<Order> orders = new ArrayList<Order>();

	@OneToMany(mappedBy = "user")
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();

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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", login=" + login + ", logo=" + logo + ", admin=" + admin + ", address=" + address
				+ ", orders=" + orders + ", orderItems=" + orderItems + "]";
	}

}
