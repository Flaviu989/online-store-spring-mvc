package com.sda.store.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Status {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idStauts;
	private String description;

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

	@Override
	public String toString() {
		return "Status [idStauts=" + idStauts + ", description=" + description + "]";
	}

}
