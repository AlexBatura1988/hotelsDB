package models;

import java.util.List;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import com.google.gson.annotations.SerializedName;

public class Customer {
	@BsonProperty("_id")
	private ObjectId id;
	@SerializedName("first_name")
	private String firstName;
	@BsonProperty("last_name")
	private String lastName;
	private String country;
	private List<ObjectId> orders;

	public Customer(ObjectId id, String firstName, String lastName, String country, List<ObjectId> orders) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.orders = orders;
	}
	
	public Customer( String firstName, String lastName, String country, List<ObjectId> orders) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.orders = orders;
	}
	
	public Customer() {}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<ObjectId> getOrders() {
		return orders;
	}

	public void setOrders(List<ObjectId> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", country=" + country
				+ ", orders=" + orders + "]";
	}
	
	

}
