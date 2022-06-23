package DBService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Updates.*;

import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Accumulators.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.*;

import models.Customer;
import models.Hotel;
import models.Order;

public class OrderDB {
	private MongoDatabase DB;
	private MongoCollection<Order> ordersCollection;
	private CustomerDB customerDB;
	private HotelDB hotelDB;
	private MongoCollection<Document> orderDocs;
	
	
	public OrderDB(MongoDatabase DB) {
		this.DB = DB;
		this.ordersCollection = DB.getCollection("orders", Order.class);
		hotelDB = new HotelDB(DB);
		customerDB = new CustomerDB(DB);
		
		
	}
	
		
	public  List<Order> getOrdersByCustomer(ObjectId customerId)
	{
		List<Order> custOrders = ordersCollection.find(Filters.eq("customer_id", customerId)).into(new ArrayList<>());
		return custOrders;
	}
	
	public Order cancelAnOrder(ObjectId orderId) {
		Order order = ordersCollection.findOneAndDelete(Filters.eq("_id", orderId));
		
		hotelDB.getHotelsCollection().findOneAndDelete(Filters.eq("_id",order.getHotelId()) );
		
		customerDB.getCustomers().findOneAndDelete(Filters.eq("_id",order.getCustomerId()));
		return order;
		
		
		
	}
	
	
	
	
	
	

}
