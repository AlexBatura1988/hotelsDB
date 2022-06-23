package runner;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.List;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import DBService.CustomerDB;
import DBService.DBSeed;
import DBService.DBService;
import DBService.HotelDB;
import DBService.OrderDB;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import connection.MyConnectionMongo;
import models.Hotel;
import models.Order;

public class Runner {
	public static void main(String[] args) {
		MongoClient mongoClient;

		DBService db = new DBService();
		// db.createHotels();
		// db.createCustomers();

		 //db.createOrders();
		
		
		Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		root.setLevel(Level.ERROR);
		CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
		
		ConnectionString connectionString = MyConnectionMongo.uri();
		MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.serverApi(ServerApi.builder().version(ServerApiVersion.V1)
						.build())
				.codecRegistry(codecRegistry)
				.build();
		mongoClient = MongoClients.create(settings);
		MongoDatabase MyDB = mongoClient.getDatabase("good_time_hotels");
		OrderDB orderDB = new OrderDB(MyDB);
		HotelDB hotelDB = new HotelDB(MyDB);
		
		
		//1. get all the orders of a customer (by id)
		List<Order> ordersList = orderDB.getOrdersByCustomer(new ObjectId("62b3629a8db3d65723bf1b4c"));
		ordersList.forEach(System.out::println);
		//2. find hotels by a city name
		List<Hotel> hotelsList = hotelDB.findHotelByCityName("Holon");
		System.out.println(hotelsList);
		
		
		
		//5.cancel an order
		 System.out.println(orderDB.cancelAnOrder(new ObjectId("62b385038f4c1f12cdd75cbd")));
		
		
		
		
		


	}

}
