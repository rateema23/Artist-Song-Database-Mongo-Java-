import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
public class MongoD {
	public static void main(String[] arges) throws IOException {
			
			//Connecting with Server
			MongoClient mongoClient = new MongoClient("localhost" , 27017);
			System.out.println("Server connection successfully done");
					
			//Connecting with database
			DB dbs = mongoClient.getDB("Artist");
			System.out.println("Connect to database successfully");
			System.out.println("Database Name "+dbs.getName());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			boolean flag=false;
			while(!flag)flag = authenticate(dbs, bufferedReader);
	}
	//Function Call
	private static boolean authenticate(DB dbs, BufferedReader bufferedReader) throws IOException{
		boolean flag = true;
		DBCollection Artist = dbs.getCollection("Artist");
		String command = null;
			while(true) {
				System.out.println("Welcome to the Artist Database, What would you like to do:"
						+ " findAll, "
						+ "find one, "
						+ "FindByAge, "
						+ "FindByHometown, "
						+ "FindByOccupation, "
						+ "FindByHometownNAge, "
						+ "insertJSON, "
						+ "Delete, "
						+ "or update from the database?");
				command = bufferedReader.readLine();
				if(command.equals("exit"))break;
				else if(command.equals("findAll"))findAll(Artist);
				else if(command.equals("find"))find(bufferedReader, Artist);
				else if(command.equals("insertJSON"))insertJSON(bufferedReader, Artist);
				else if(command.equals("insert"))insert(bufferedReader, Artist);
				else if(command.equals("delete"))delete(bufferedReader, Artist);
				else if(command.equals("update"))update(bufferedReader, Artist);
				else if(command.equals("FindByAge"))FindByAge(bufferedReader, Artist);
				else if(command.equals("FindByHometown"))FindByHometown(bufferedReader, Artist);
				else if(command.equals("FindByOccupation"))FindByOccupation(bufferedReader, Artist);
				else if(command.equals("FindByHometownNAge"))FindByHometownNAge(bufferedReader, Artist);
			}
			return flag;
		}
			
	
	
	
	//Update
	private static void update(BufferedReader bufferedReader, DBCollection Artist) throws IOException {
	//Update Name
		System.out.println("Update name from: ");
		DBObject fromDBObject = new BasicDBObject();
		fromDBObject.put("Name",  bufferedReader.readLine());
		
		System.out.println("Update name to: ");
		DBObject toDBObject = new BasicDBObject();
		toDBObject.put("Name",  bufferedReader.readLine());
		
		DBObject updateDBObject = new BasicDBObject();
		updateDBObject.put("$set",  toDBObject);
		Artist.update(fromDBObject, updateDBObject);
		
	//Update Age
		String str;
		int ivar;
		int ivar1;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Update Age from: ");
		str = br.readLine();
		ivar = Integer.parseInt(str);
		DBObject fromDBObject1 = new BasicDBObject();
		fromDBObject1.put("Age", ivar);
				
		System.out.println("Update Age to: ");
		str = br.readLine();
		ivar1 = Integer.parseInt(str);
		DBObject toDBObject1 = new BasicDBObject();
		toDBObject1.put("Age",  ivar1);
		
		
		DBObject updateDBObject1 = new BasicDBObject();
		updateDBObject1.put("$set",  toDBObject1);
		Artist.update(fromDBObject1, updateDBObject1);
		
	//Update Home town
		System.out.println("Update hometown from: ");
		DBObject HTfromDBObject = new BasicDBObject();
		HTfromDBObject.put("Hometown",  bufferedReader.readLine());
		
		System.out.println("Update hometown to: ");
		DBObject HTtoDBObject = new BasicDBObject();
		HTtoDBObject.put("Hometown",  bufferedReader.readLine());
		
		DBObject HTupdateDBObject = new BasicDBObject();
		HTupdateDBObject.put("$set",  HTtoDBObject);
		Artist.update(HTfromDBObject, HTupdateDBObject);
		
	//Update Occupation
		System.out.println("Update Occupation from: ");
		DBObject OfromDBObject = new BasicDBObject();
		OfromDBObject.put("Occupation",  bufferedReader.readLine());
		
		System.out.println("Update Occupation to: ");
		//DBObject OtoDBObject = new BasicDBObject();
		//OtoDBObject.put("Occupation",  bufferedReader.readLine());
		
		/*DBObject OupdateDBObject = new BasicDBObject();
		OupdateDBObject.put("$set",  OtoDBObject);
		Artist.update(OfromDBObject, OupdateDBObject);	
		*/
		String occupation = bufferedReader.readLine();
		ArrayList<String> occupationArray = new ArrayList<String>();
		BasicDBList occupationDBList = new BasicDBList();
		DBObject occupationDBObject = null;
		occupationArray.add(occupation);
		
		
		
		DBObject modifiedObject =new BasicDBObject();
		modifiedObject.put("$push", new BasicDBObject().append("Occupation", occupationArray));
		Artist.update(OfromDBObject, modifiedObject);
		
		
		//nameDBObject.put("occupation", occupationArray);
		//System.out.println(nameDBObject);
		//Artist.insert(nameDBObject);
		
		
		
	}
	//Delete Artist
	private static void delete(BufferedReader bufferedReader, DBCollection Artist) throws IOException {
		System.out.println("Who would you like to delete? ");
		DBObject basicDBObject = new BasicDBObject();
		basicDBObject.put("Name",  bufferedReader.readLine());
		Artist.remove(basicDBObject);
		
	}
	//Insert Artist Info
	private static void insert(BufferedReader bufferedReader, DBCollection Artist)throws IOException {
		System.out.println("Name");
		String name = bufferedReader.readLine();
		DBObject nameDBObject = new BasicDBObject();
		nameDBObject.put("Name", name);
		
		
		System.out.println("Age");
		String Age = bufferedReader.readLine();
		nameDBObject.put("Age", Integer.parseInt(Age));
		
		
		System.out.println("Hometown");
		String hometown = bufferedReader.readLine();
		nameDBObject.put("Hometown", hometown);
		
		
System.out.println("Occupation");
		String occupation = bufferedReader.readLine();
		ArrayList<String> occupationArray = new ArrayList<String>();
		BasicDBList occupationDBList = new BasicDBList();
		DBObject occupationDBObject = null;
		occupationArray.add(occupation);
		nameDBObject.put("occupation", occupationArray);
		//System.out.println(nameDBObject);
		Artist.insert(nameDBObject);
	}
	
	
	
	

	//Insert Artist Line
	private static void insertJSON(BufferedReader bufferedReader, DBCollection Artist) throws IOException {
		System.out.println("JSON Line: ");
		Artist.insert((DBObject)JSON.parse(bufferedReader.readLine()));
	}
	
	
	
	//Find All in Database
	private static void findAll(DBCollection Artist) {
		DBCursor dbCursor = Artist.find();
		while(dbCursor.hasNext())System.out.println(dbCursor.next());
		System.out.println("Documents in your database");
		System.out.println(Artist.getCount());
	}
	
	
	
	//Find One in Database
	private static void find(BufferedReader bufferedReader, DBCollection Artist) throws IOException {
		System.out.println("Who would you like to find? ");
		BasicDBObject doc = new BasicDBObject("Name", bufferedReader.readLine());
		Cursor cursor=Artist.find(doc);
		while(cursor.hasNext()) {
			System.out.println(cursor.next());
		}
		
			}
	
	
	//Find by Age in Database
		private static void FindByAge(BufferedReader bufferedReader, DBCollection Artist) throws IOException {
			String str;
			int ivar1;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Which age group of Artist would you like to find? ");
			str = br.readLine();
			ivar1 = Integer.parseInt(str);
			BasicDBObject doc = new BasicDBObject("Age", ivar1);
			Cursor cursor=Artist.find(doc);
			while(cursor.hasNext()) {
				System.out.println(cursor.next());
			}
			
				}
	//Find by Home town
		private static void FindByHometown(BufferedReader bufferedReader, DBCollection Artist) throws IOException {
			System.out.println("From which Artist hometown would you like to find? ");
			BasicDBObject doc = new BasicDBObject("Hometown", bufferedReader.readLine());
			Cursor cursor=Artist.find(doc);
			while(cursor.hasNext()) {
				System.out.println(cursor.next());
			
		}
		}
	//Find by Occupation	
		private static void FindByOccupation(BufferedReader bufferedReader, DBCollection Artist) throws IOException {
			System.out.println("Which Artist occupation would you like to find ");
			BasicDBObject doc = new BasicDBObject("Occupation", bufferedReader.readLine());
			Cursor cursor=Artist.find(doc);
			while(cursor.hasNext()) {
				System.out.println(cursor.next());
			
		}
			
}
		
		
		//Find by Occupation and Age	
		private static void FindByHometownNAge(BufferedReader bufferedReader, DBCollection Artist) throws IOException {
			System.out.println("Which Artist Hometown and Age would you like to find ");{
				//String str = null;
				
				int ivar1 = Integer.parseInt(bufferedReader.readLine());
				String HomeTown = bufferedReader.readLine();
				
				
				
				BasicDBObject andQuery = new BasicDBObject();
				List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
				obj.add(new BasicDBObject("Age", ivar1));
				obj.add(new BasicDBObject("Hometown", HomeTown));
//				andQuery = (BasicDBObject) obj;
				andQuery.put("$and",obj);
				//System.out.println(andQuery.toString());

				DBCursor cursor=Artist.find(andQuery);
				while(cursor.hasNext()) {
					System.out.println(cursor.next());
				
				
			}
						
			
		}
		
		
		
		
			
			
			
			
			
			
			/*System.out.println("Which Artist Hometown and Age would you like to find ");{
				String str;
				int ivar1;
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				//System.out.println("Which age group of Artist would you like to find? ");
				str = br.readLine();
				ivar1 = Integer.parseInt(str);
				
				
				BasicDBObject andQuery = new BasicDBObject();
				List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
				obj.add(new BasicDBObject("Homwtown", bufferedReader.readLine()));
				obj.add(new BasicDBObject("Age", ivar1));
				andQuery.put("$and", obj);

				System.out.println(andQuery.toString());
				DBCursor cursor = Artist.find(andQuery);
				while (cursor.hasNext()) {
					System.out.println(cursor.next());
				}
			
			
			}
		
		}

	
		
		private static void FindByOccupationNAge(BufferedReader bufferedReader, DBCollection Artist) throws IOException {
			System.out.println("Which Artist occupation would you like to find ");
			BasicDBObject doc = new BasicDBObject("Occupation", bufferedReader.readLine());
			Cursor cursor=Artist.find(doc);
			while(cursor.hasNext()) {
				System.out.println(cursor.next());
			
		}
			String str;
			int ivar1;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Which age group of Artist would you like to find? ");
			str = br.readLine();
			ivar1 = Integer.parseInt(str);
			BasicDBObject doc1 = new BasicDBObject("Age", ivar1);
			Cursor cursor1=Artist.find(doc);
			while(cursor1.hasNext()) {
				System.out.println(cursor1.next());
			}
}*/
}
}