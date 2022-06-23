package models;

public class Address {
	private String street;
	private int number;
	private String City;
	private String country;
	
	public Address(String street, int number, String city, String country) {
		this.street = street;
		this.number = number;
		City = city;
		this.country = country;
	}
	
	public Address() {}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Address [street=" + street + ", number=" + number + ", City=" + City + ", country=" + country + "]";
	}
	
	
	
	
	
	

}
