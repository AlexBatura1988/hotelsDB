package DBService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

import ch.qos.logback.core.filter.Filter;
import models.Address;
import models.Customer;
import models.Hotel;
import models.Order;
import models.Room;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

public class DBSeed {

	public static final ObjectId HERMOSO_ID = new ObjectId();
	public static final ObjectId LINDO_ID = new ObjectId();
	public static final ObjectId BELLO_ID = new ObjectId();
	public static final ObjectId CUSTOMER_ID_1 = new ObjectId();
	public static final ObjectId CUSTOMER_ID_2 = new ObjectId();
	public static final ObjectId CUSTOMER_ID_3 = new ObjectId();

	public static MongoCollection<Hotel> createHotelsCollection(MongoClient mongoClient) {

		MongoDatabase myDB = mongoClient.getDatabase("good_time_hotels");
		MongoCollection<Hotel> hotels = myDB.getCollection("hotels", Hotel.class);

		hotels.drop();
		List<Hotel> hotelsList = Arrays.asList(
				new Hotel(HERMOSO_ID, "Hermoso", new Address("Sokolov", 2, "Haifa", "Israel"), 6,
						Arrays.asList(new Room(new ObjectId(), 3, true), new Room(new ObjectId(), 3, true),
								new Room(new ObjectId(), 3, true), new Room(new ObjectId(), 3, false)),
						600, new ArrayList<ObjectId>()),

				new Hotel(LINDO_ID, "Lindo", new Address("Hankin", 12, "Holon", "Israel"), 9,
						Arrays.asList(new Room(new ObjectId(), 4, true), new Room(new ObjectId(), 3, false),
								new Room(new ObjectId(), 2, false)),
						900, new ArrayList<ObjectId>()),

				new Hotel(BELLO_ID, "Bello", new Address("halper", 19, "Bat-Yam", "Israel"), 4,
						Arrays.asList(new Room(new ObjectId(), 3, true), new Room(new ObjectId(), 3, false)), 1200,
						new ArrayList<ObjectId>())

		);

		InsertManyResult result = hotels.insertMany(hotelsList);
		System.out.println(result);

		return hotels;

	}

	public static MongoCollection<Customer> createCustomersCollection(MongoClient mongoClient) {
		MongoDatabase myDB = mongoClient.getDatabase("good_time_hotels");

		MongoCollection<Customer> customers = myDB.getCollection("customers", Customer.class);
		customers.drop();
		List<Customer> customersList = Arrays.asList(
				new Customer(CUSTOMER_ID_1, "Alex", "Batura", "Israel", new ArrayList<ObjectId>()),
				new Customer(CUSTOMER_ID_2, "Moshe", "More", "Russia", new ArrayList<ObjectId>()),
				new Customer(CUSTOMER_ID_3, "Victor", "Koen", "Kiev", new ArrayList<ObjectId>()));

		InsertManyResult result = customers.insertMany(customersList);
		System.out.println(result);

		return customers;

	}

	public static MongoCollection<Order> createOrdersCollection(MongoClient mongoClient) {
		MongoDatabase myDB = mongoClient.getDatabase("good_time_hotels");
		MongoCollection<Order> ordersCollection = myDB.getCollection("orders", Order.class);
		MongoCollection<Customer> customersCollection = myDB.getCollection("customers", Customer.class);
		MongoCollection<Hotel> hotelsCollection = myDB.getCollection("hotels", Hotel.class);
		
		ordersCollection.drop();
		//customersCollection.drop();
		hotelsCollection.drop();
		createHotelsCollection(mongoClient);
		List<Order> orders = new ArrayList<Order>();
		Order order1 = new Order(new ObjectId(), HERMOSO_ID, CUSTOMER_ID_1, LocalDate.now(), LocalDate.of(2022, 1, 1),
				2, 1200);
		Order order2 = new Order(new ObjectId(), LINDO_ID, CUSTOMER_ID_1, LocalDate.now(), LocalDate.of(2022, 1, 1), 4,
				3200);
		orders.add(order1);
		orders.add(order2);

		InsertManyResult result = ordersCollection.insertMany(orders);

		//order1.getId();
		//order2.getId();

		
		
        Bson hermosoFilter = Filters.eq("_id", HERMOSO_ID);
		// 1 read hotel by name
		Hotel hotel = hotelsCollection.find().first();
		System.out.println(hotel);
		//2 add new order
		List<ObjectId> hotelOrders = hotel.getOrders();
		hotelOrders.add(order1.getId());
		
		hotel.setOrders(hotelOrders);
		
		//3 update DB
		FindOneAndReplaceOptions findOneAndReplaceOptions = new FindOneAndReplaceOptions().returnDocument(ReturnDocument.AFTER);
		Hotel updatedHotel = hotelsCollection.findOneAndReplace(hermosoFilter, hotel, findOneAndReplaceOptions);
		
		
		System.out.println(updatedHotel);
		
		//update customer
		Bson customerFilter = Filters.eq("_id",CUSTOMER_ID_1);
		Customer customer = customersCollection.find().first();
		System.out.println(customer);
		List<ObjectId> customerOrders = customer.getOrders();
		customerOrders.add(order2.getId());
		customerOrders.add(order1.getId());
		customer.setOrders(customerOrders);
		FindOneAndReplaceOptions findOneAndReplaceOptions1 = new FindOneAndReplaceOptions().returnDocument(ReturnDocument.AFTER);
		Customer updateCustomer = customersCollection.findOneAndReplace(customerFilter, customer,findOneAndReplaceOptions1);
		System.out.println(updateCustomer);
		
		
		 
		

		
		return ordersCollection;
	}

}
