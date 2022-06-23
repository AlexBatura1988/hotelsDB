package DBService;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.json.JsonWriterSettings;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import connection.MyConnectionMongo;
import models.Hotel;
import models.Order;

public class DBService {
	
	
	 
	
	protected static ConnectionString connectionString;
	MongoClient mongoClient;
	//test
	//MongoDatabase myDB = mongoClient.getDatabase("good_time_hotels");
	//MongoCollection<Hotel> hotelsCollection = myDB.getCollection("hotels", Hotel.class);
	
	JsonWriterSettings prettyPrint = JsonWriterSettings.builder().indent(true).build();

	public DBService() {
		connect();
	}

	public void connect() {
		Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		root.setLevel(Level.ERROR);
		CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
		try {
		ConnectionString connectionString = MyConnectionMongo.uri();
		MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.serverApi(ServerApi.builder().version(ServerApiVersion.V1)
						.build())
				.codecRegistry(codecRegistry)
				.build();
		mongoClient = MongoClients.create(settings);
		System.out.println("connect to DB");
		//mongoClient.listDatabaseNames().forEach(System.out::println);
		}catch (Exception e) {
			System.out.println("cannot connect to db");
		}
		

	}
	
	public void createHotels() {
		DBSeed.createHotelsCollection(mongoClient);
		
	}
	public void createCustomers() {
		DBSeed.createCustomersCollection(mongoClient);
	}
	
	public void createOrders() {
		DBSeed.createOrdersCollection(mongoClient);
	}
	
	
	
	


}
