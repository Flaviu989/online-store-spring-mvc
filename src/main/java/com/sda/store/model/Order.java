package com.sda.store.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "[order]")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOrder;
	private double totalCost;
	private Date dateofOrder;

	@OneToOne
	@JoinColumn(name = "idAddress")
	private Address deliveryAddress;

	@ManyToOne
	@JoinColumn(name = "idUser")
	private User user;

	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderItems;

	@ManyToOne
	@JoinColumn(name = "idStatus")
	private Status status;

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public Date getDateofOrder() {
		return dateofOrder;
	}

	public void setDateofOrder(Date dateofOrder) {
		this.dateofOrder = dateofOrder;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "Order [idOrder=" + idOrder + ", totalCost=" + totalCost + ", dateofOrder=" + dateofOrder
				+ ", deliveryAddress=" + deliveryAddress + ", user=" + user + ", orderItems=" + orderItems + ", status="
				+ status + "]";
	}

}
