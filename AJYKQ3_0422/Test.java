package me.AJYKQ3.mongo.mongoapp;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.lt;

import java.util.Arrays;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		
		MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase db = mongoClient.getDatabase("AJYKQ3");
        com.mongodb.client.MongoCollection<Document> table = db.getCollection("mozi");
        Document doc = new Document();
        BasicDBObject query = new BasicDBObject();

		System.out.println("Step 1 *************************");
            
        query = new BasicDBObject();
        doc = table.find().first();
        String res = doc.getString("nev");
        System.out.println(res);
        
		System.out.println("Step 2 *************************");
        query = new BasicDBObject();
        for (Document doc2 :  table.find()) {
        	System.out.println(doc2.getString("nev") );
        }
        
		System.out.println("Step 3 *************************");
        
        MongoCursor<Document> cursor = (MongoCursor<Document>) table.find(lt("hely", "Miskolc")).iterator();
        try {
            while(cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
		System.out.println("Step 4 *************************");

		Document nmozi = new Document("_id", 111)
				.append("nev", "Cinema City")
			    .append("hely", "Margaréta")
			    .append("allapot", "Új");
		
		table.insertOne(nmozi);

        System.out.println("Step 5 *************************");

        Document toUpdate = new Document();
        toUpdate.put("hely", "Tokaj");

        Document updateTo = new Document();
        updateTo .put("hely", "Szeged");

        Document updateObject = new Document();
        updateObject.put("$set", updateTo);

        table.updateOne(toUpdate, updateObject);

        System.out.println("Step 6 *************************");

        Document deleteQuery = new Document();
        deleteQuery.put("hely", "Pécs");

        table.deleteOne(deleteQuery);

        System.out.println("Step 7 *************************");

        List<Document> moziLista = Arrays.asList(
                    new Document().append("nev", "teszt").append("hely", "Dunaújváros").append("category", "Pizza"),
                    new Document().append("nev", "teszt2").append("hely", "Ózd").append("category", "Salad"));
        table.insertMany(moziLista);        
		
		System.out.println("OK *************************");

		mongoClient.close();

        
	}
	

}
