package com.product.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book implements Comparable<Product> {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int id;
	public String genre;
	public String authour;
	public String publications;
	
	public Book() {
		super();
	}

	public Book(int id, String genre, String authour, String publications) {
		super();
		this.id = id;
		this.genre = genre;
		this.authour = authour;
		this.publications = publications;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getAuthour() {
		return authour;
	}

	public void setAuthour(String authour) {
		this.authour = authour;
	}

	public String getPublications() {
		return publications;
	}

	public void setPublications(String publications) {
		this.publications = publications;
	}

	public int compareTo(Book o) {
		if (this.id > o.id) {
			return 1;
		} else if (this.id < o.id) {
			return -1;
		} else {
			return 0;
		}
	}

	@Override
	public int compareTo(Product o) {
		if (this.id > o.id) {
			return 1;
		} else if (this.id < o.id) {
			return -1;
		} else {
			return 0;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(authour, genre, id, publications);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(authour, other.authour) && Objects.equals(genre, other.genre) && id == other.id
				&& Objects.equals(publications, other.publications);
	}
	
	

}