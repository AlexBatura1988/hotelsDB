package DBService;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import models.Customer;

public class CustomerDB {
	private MongoDatabase MyDB;
	private MongoCollection<Customer> customers;
	
	public CustomerDB(MongoDatabase MyDB) {
		this.MyDB = MyDB;
		this.customers = MyDB.getCollection("customers",Customer.class);
	}

	public MongoCollection<Customer> getCustomers() {
		return customers;
	}
	
	

}
