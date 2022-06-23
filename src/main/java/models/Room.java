package models;

import org.bson.types.ObjectId;

public class Room {
	private ObjectId id;
	private int number;
	private boolean isBath;
	
	public Room(ObjectId id, int number, boolean isBath) {
		this.id = id;
		this.number = number;
		this.isBath = isBath;
	}
	
	public Room( int number, boolean isBath) {
		this.number = number;
		this.isBath = isBath;
	}
	
	public Room() {}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isBath() {
		return isBath;
	}

	public void setBath(boolean isBath) {
		this.isBath = isBath;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", number=" + number + ", isBath=" + isBath + "]";
	}
	
	
	
	

}
