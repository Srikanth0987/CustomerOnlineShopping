package com.product.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Apparsal implements Comparable<Product>{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int id;
	public String type;
	public String brand;
	public String design;

	public Apparsal() {
		super();
	}

	public Apparsal(int id, String type, String brand, String design) {
		super();
		this.id = id;
		this.type = type;
		this.brand = brand;
		this.design = design;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDesign() {
		return design;
	}

	public void setDesign(String design) {
		this.design = design;
	}

	public int compareTo(Apparsal o) {
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
		return Objects.hash(brand, design, id, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Apparsal other = (Apparsal) obj;
		return Objects.equals(brand, other.brand) && Objects.equals(design, other.design) && id == other.id
				&& Objects.equals(type, other.type);
	}
	
	
}