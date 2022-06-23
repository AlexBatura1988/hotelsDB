package models;

import java.time.LocalDate;
import java.util.Date;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import com.google.gson.annotations.SerializedName;

public class Order {
	@BsonProperty("_id")
	private ObjectId id;
	@BsonProperty("hotel_id")
	private ObjectId hotelId;
	@BsonProperty("customer_id")
	private ObjectId customerId;
	@BsonProperty("order_date")
	private LocalDate orderDate;
	@BsonProperty("start_date")
	private LocalDate startDate;
	@BsonProperty("number_of_night")
	private int numberOfNight;
	@BsonProperty("total_price")
	private double totalPrice;
	
	public Order(ObjectId id, ObjectId hotelId, ObjectId customerId, LocalDate orderDate, LocalDate startDate, int numberOfNight,
			double totalPrice) {
		
		this.id = id;
		this.hotelId = hotelId;
		this.customerId = customerId;
		this.orderDate = orderDate;
		this.startDate = startDate;
		this.numberOfNight = numberOfNight;
		this.totalPrice = totalPrice;
	}
	
	public Order(ObjectId hotelId, ObjectId customerId, LocalDate orderDate, LocalDate startDate, int numberOfNight,
			double totalPrice) {
		
		this.hotelId = hotelId;
		this.customerId = customerId;
		this.orderDate = orderDate;
		this.startDate = startDate;
		this.numberOfNight = numberOfNight;
		this.totalPrice = totalPrice;
	}
	public Order() {}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public ObjectId getHotelId() {
		return hotelId;
	}

	public void setHotelId(ObjectId hotelId) {
		this.hotelId = hotelId;
	}

	public ObjectId getCustomerId() {
		return customerId;
	}

	public void setCustomerId(ObjectId customerId) {
		this.customerId = customerId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public int getNumberOfNight() {
		return numberOfNight;
	}

	public void setNumberOfNight(int numberOfNight) {
		this.numberOfNight = numberOfNight;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", hotelId=" + hotelId + ", customerId=" + customerId + ", orderDate=" + orderDate
				+ ", startDate=" + startDate + ", numberOfNight=" + numberOfNight + ", totalPrice=" + totalPrice + "]";
	}
	
	
	
	
	
	
	

}
