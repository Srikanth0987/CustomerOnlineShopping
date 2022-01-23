package com.product.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Cart implements Comparable<Cart> {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int id;
    @OneToMany(fetch = FetchType.EAGER)
	private Set<Product> Product;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	private int count;
	
	public Cart() {
		super();
	}

	
	public Cart(int id, Set<com.product.model.Product> product, User user, int count) {
		super();
		this.id = id;
		Product = product;
		this.user = user;
		this.count = count;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Product> getProduct() {
		return Product;
	}

	public void setProduct(Set<Product> product) {
		Product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int compareTo(Cart o) {
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
		return Objects.hash(Product, count, id, user);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		return Objects.equals(Product, other.Product) && count == other.count && id == other.id
				&& Objects.equals(user, other.user);
	}

}