package com.product.model;


import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User implements Comparable<User> {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long userId;
	public String username;
	public String password;
	public String type;
	
	public User() {
		super();
	}
	
	public User(long userId, String username, String password, String type) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.type = type;
	}

	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int compareTo(User o) {
		if (this.userId > o.userId) {
			return 1;
		} else if (this.userId < o.userId) {
			return -1;
		} else {
			return 0;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(password, type, userId, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(password, other.password) && Objects.equals(type, other.type) && userId == other.userId
				&& Objects.equals(username, other.username);
	}
	
	
}