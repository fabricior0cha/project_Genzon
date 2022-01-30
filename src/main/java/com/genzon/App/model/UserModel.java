package com.genzon.App.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_users")
public class UserModel {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String document;
	
	private String token;
	
	@OneToMany(mappedBy = "user")
	Set<PurchaseModel> purchases;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Set<PurchaseModel> getPurchases() {
		return purchases;
	}

	public void setPurchases(Set<PurchaseModel> purchases) {
		this.purchases = purchases;
	}
	
	
	
	
	
}
