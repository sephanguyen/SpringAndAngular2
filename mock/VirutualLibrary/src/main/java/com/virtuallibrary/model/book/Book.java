package com.virtuallibrary.model.book;

import com.virtuallibrary.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book extends BaseModel {

	private int price;

	private String name;

	public Book() {}

	public Book(Long id, String name, int price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	//Add this for avoid compiled errors
	public Book(Long id, String name, int price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Long getId() {
		return this.id;
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

	public int getPrice()	{
		return price;
	}

	public void setPrice(int price)	{
		this.price = price;
	}
}
