package ar.edu.unq.epers.bichomon.backend.dao.mongodb;

import com.mongodb.MongoClient;
import org.jongo.Jongo;

/**
 * As per documentation:
 * 
 * "The MongoClient instance actually represents a pool of connections to the database;
 * you will only need one instance of class MongoClient even with multiple threads."
 * 
 * This singleton ensures that only one instance of MongoClient ever exists
 */
public class MongoConnection {
	
	private static MongoConnection INSTANCE;

	private MongoClient client;
	private Jongo jongo;
	
	public static synchronized MongoConnection getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MongoConnection();
		}
		return INSTANCE;
	}
	
	@SuppressWarnings("deprecation")
	private MongoConnection() {
		this.client = new MongoClient("localhost", 27017);
		this.jongo = new Jongo(this.client.getDB("bichomon"));
	}
	
	public Jongo getJongo() {
		return this.jongo;
	}

}
