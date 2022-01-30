package com.genzon.App.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_purchases")
public class PurchaseModel {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	
	@ManyToOne
	@JoinColumn(name = "fk_user")
	UserModel user;
	
	@ManyToOne
	@JoinColumn(name = "fk_product")
	ProductModel product;
	
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public ProductModel getProductModel() {
		return product;
	}

	public void setProductModel(ProductModel product) {
		this.product = product;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
	
}
