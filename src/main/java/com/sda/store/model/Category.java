package com.sda.store.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCategory;
	private String name;

	@ManyToOne
	@JoinColumn(name = "idSuperCategory")
	private Category superCategory;

	@OneToMany(mappedBy = "superCategory", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Category> subCategories = new ArrayList<Category>();

	@OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Product> products = new ArrayList<Product>();

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getSuperCategory() {
		return superCategory;
	}

	public void setSuperCategory(Category superCategory) {
		this.superCategory = superCategory;
	}

	public List<Category> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<Category> subCategories) {
		this.subCategories = subCategories;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Category [idCategory=" + idCategory + ", name=" + name + ", subCategories=" + subCategories
				+ ", products=" + products + "]";
	}

}
