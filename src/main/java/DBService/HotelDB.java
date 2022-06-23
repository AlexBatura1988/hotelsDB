package DBService;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import models.Hotel;

public class HotelDB {
	private MongoDatabase myDB;
	private MongoCollection<Hotel> hotelsCollection;
	
	public HotelDB(MongoDatabase myDB) {
		this.myDB = myDB;
		this.hotelsCollection = myDB.getCollection("hotels",Hotel.class);
	}
	
	
	
	
	
	public MongoCollection<Hotel> getHotelsCollection() {
		return hotelsCollection;
	}





	public List<Hotel> findHotelByCityName(String city){
		List<Hotel> hotels = hotelsCollection.find(Filters.eq("address.city", city)).into(new ArrayList<>());
		return hotels;
	}

}
