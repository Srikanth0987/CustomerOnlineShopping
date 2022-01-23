package com.product.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Product implements Comparable<Product> {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int id;
	public String name;
	public Double price;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "book_id")
	private Book book;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "apparsal_id")
	private Apparsal apparsal;
	
	public Product() {
		super();
	}
	
	public Product(int id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public int compareTo(Product o) {
		if (this.id > o.id) {
			return 1;
		} else if (this.id < o.id) {
			return -1;
		} else {
			return 0;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Apparsal getApparsal() {
		return apparsal;
	}

	public void setApparsal(Apparsal apparsal) {
		this.apparsal = apparsal;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apparsal, book, id, name, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(apparsal, other.apparsal) && Objects.equals(book, other.book) && id == other.id
				&& Objects.equals(name, other.name) && Objects.equals(price, other.price);
	}

}