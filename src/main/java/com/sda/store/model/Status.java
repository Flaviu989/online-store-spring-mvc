package com.sda.store.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Status {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idStauts;
	private String description;

	@OneToOne
	@JoinColumn(name = "idOrder")
	private Order order;

	public int getIdStauts() {
		return idStauts;
	}

	public void setIdStauts(int idStauts) {
		this.idStauts = idStauts;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Status [idStauts=" + idStauts + ", description=" + description + ", order=" + order + "]";
	}

}
