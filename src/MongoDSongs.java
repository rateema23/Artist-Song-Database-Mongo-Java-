import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
public class MongoDSongs {

	public static void main(String[] args)throws IOException {
			
			//Connecting with Server
			MongoClient mongoClient = new MongoClient("localhost" , 27017);
			System.out.println("Server connection successfully done");
					
			//Connecting with database
			DB dbs = mongoClient.getDB("Songs");
			System.out.println("Connect to database successfully");
			System.out.println("Database Name "+dbs.getName());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			boolean flag=false;
			while(!flag)flag = authenticate(dbs, bufferedReader);
	}
//Function Call
private static boolean authenticate(DB dbs, BufferedReader bufferedReader) throws IOException{
	boolean flag = true;
	DBCollection Songs = dbs.getCollection("Songs");
	String command = null;
		while(true) {
			System.out.println("Welcome to the Songs Database, What would you like to do: findAll, find one, findByProducer, findByArtistName, findByGenre, findByDate, findByAlbum, findByProducer, findBySongWriter,  insertJSON, Delete, or update from the database?");
			command = bufferedReader.readLine();
			if(command.equals("exit"))break;
			else if(command.equals("findAll"))findAll(Songs);
			else if(command.equals("find"))find(bufferedReader, Songs);
			else if(command.equals("findProducer"))findProducer(bufferedReader, Songs);
			else if(command.equals("insertJSON"))insertJSON(bufferedReader, Songs);
			else if(command.equals("insert"))insert(bufferedReader, Songs);
			else if(command.equals("delete"))delete(bufferedReader, Songs);
			else if(command.equals("update"))update(bufferedReader, Songs);
			else if(command.equals("findByArtistName"))findByArtistName(bufferedReader, Songs);
			else if(command.equals("findByGenre"))findByGenre(bufferedReader, Songs);
			else if(command.equals("findByDate"))findByDate(bufferedReader, Songs);
			else if(command.equals("findByAlbum"))findByAlbum(bufferedReader, Songs);
			else if(command.equals("findByProducer"))findByProducer(bufferedReader, Songs);
			else if(command.equals("findBySongWriter"))findBySongWriter(bufferedReader, Songs);
		}
		
		return flag;
	}
		


//Update Songs
private static void update(BufferedReader bufferedReader, DBCollection Songs) throws IOException {
	//Update Title
	System.out.println("Update Title from: ");
	DBObject fromDBObject = new BasicDBObject();
	fromDBObject.put("Song Title",  bufferedReader.readLine());
	
	System.out.println("Update Tile to: ");
	DBObject toDBObject = new BasicDBObject();
	toDBObject.put("Song Title",  bufferedReader.readLine());
	
	DBObject updateDBObject = new BasicDBObject();
	updateDBObject.put("$set",  toDBObject);
	Songs.update(fromDBObject, updateDBObject);
	
	//Update Artist
	System.out.println("Update Artist from: ");
	DBObject fromDBObject1 = new BasicDBObject();
	fromDBObject1.put("ArtistNmae",  bufferedReader.readLine());
	
	System.out.println("Update Artist to: ");
	DBObject toDBObject1 = new BasicDBObject();
	toDBObject1.put("ArtistName",  bufferedReader.readLine());
	
	DBObject updateDBObject1 = new BasicDBObject();
	updateDBObject1.put("$set",  toDBObject1);
	Songs.update(fromDBObject1, updateDBObject1);
	
	//update Genre
	System.out.println("Update Genre from: ");
	DBObject fromDBObject11 = new BasicDBObject();
	fromDBObject11.put("Genre",  bufferedReader.readLine());
	
	System.out.println("Update Genre to: ");
	DBObject toDBObject11 = new BasicDBObject();
	toDBObject11.put("Genre",  bufferedReader.readLine());
	
	DBObject updateDBObject11 = new BasicDBObject();
	updateDBObject11.put("$set",  toDBObject11);
	Songs.update(fromDBObject11, updateDBObject11);
	
	//update ReleaseDate
	System.out.println("Update Date Release from: ");
	DBObject fromDBObject111 = new BasicDBObject();
	fromDBObject111.put("Date Release",  bufferedReader.readLine());
	
	System.out.println("Update Date Release to: ");
	DBObject toDBObject111 = new BasicDBObject();
	toDBObject111.put("Date Release",  bufferedReader.readLine());
	
	DBObject updateDBObject111 = new BasicDBObject();
	updateDBObject111.put("$set",  toDBObject111);
	Songs.update(fromDBObject111, updateDBObject111);
	
	//update Album
	System.out.println("Update Album from: ");
	DBObject fromDBObject1111 = new BasicDBObject();
	fromDBObject1111.put("Album",  bufferedReader.readLine());
	
	System.out.println("Update Album to: ");
	DBObject toDBObject1111 = new BasicDBObject();
	toDBObject1111.put("Album",  bufferedReader.readLine());
	
	DBObject updateDBObject1111 = new BasicDBObject();
	updateDBObject1111.put("$set",  toDBObject1111);
	Songs.update(fromDBObject1111, updateDBObject1111);
	
	//update Producer
	System.out.println("Update Producer Name from: ");
	DBObject fromDBObject2 = new BasicDBObject();
	fromDBObject2.put("Producer.Name",  bufferedReader.readLine());
	
	
	System.out.println("Update Producer Name to: ");
	DBObject toDBObject2 = new BasicDBObject();
	toDBObject2.put("Producer.Name",  bufferedReader.readLine());
	
	DBObject updateDBObject2 = new BasicDBObject();
	updateDBObject2.put("$set",  toDBObject2);
	Songs.update(fromDBObject2, updateDBObject2);
	
	
	
	

	
	
	
	
	/*
	//update song writer
	System.out.println("SongWriter");
	String SongWriter = bufferedReader.readLine();
	String[] temp2 = Producer.split(",");
	int i1 = 0;
	BasicDBList SongWriterDBList = new BasicDBList();
	DBObject SongWriterDBObject = null;
	while(i1<temp.length) {
		SongWriterDBObject = new BasicDBObject();
		SongWriterDBObject.put("Name", temp[i1++]);
		SongWriterDBList.add(SongWriterDBObject);
	}
	//nameDBObject.put("occupation", occupationDBList.toArray());
	Songs.insert(SongWriterDBObject);*/
}

//Delete Songs
private static void delete(BufferedReader bufferedReader, DBCollection Songs) throws IOException {
	System.out.println("What song would you like to delete? ");
	DBObject basicDBObject = new BasicDBObject();
	basicDBObject.put("Song Title",  bufferedReader.readLine());
	Songs.remove(basicDBObject);
	
}
//Insert Songs Info
private static void insert(BufferedReader bufferedReader, DBCollection Songs)throws IOException {
	System.out.println("Songs Title");
	String name = bufferedReader.readLine();
	DBObject nameDBObject = new BasicDBObject();
	nameDBObject.put("Song Title", name);
	
	System.out.println("Artist Name");
	String Name = bufferedReader.readLine();
	nameDBObject.put("ArtistName", Name);
	
			
	System.out.println("Genre");
	String Genre = bufferedReader.readLine();
	BasicDBList GenreDBList = new BasicDBList();
	DBObject GenreDBObject = null;
	
	nameDBObject.put("Genre", GenreDBList.toArray());
	Songs.insert(nameDBObject);
	
	
	System.out.println("Date Release");
	String Date = bufferedReader.readLine();
	nameDBObject.put("Date Release", Date);
	
	
	
	System.out.println("Album");
	String Album = bufferedReader.readLine();
	nameDBObject.put("Album", Album);
	
	
		
	System.out.println("Producer");
	String Producer = bufferedReader.readLine();
	String[] temp1 = Producer.split(",");
	int i1 = 0;
	BasicDBList ProducerDBList = new BasicDBList();
	DBObject ProducerDBObject = null;
	while(i1<temp1.length) {
		ProducerDBObject = new BasicDBObject();
		ProducerDBObject.put(Producer, ProducerDBObject);
		ProducerDBList.add(ProducerDBObject);
	}
	nameDBObject.put("Producer", ProducerDBList.toArray());
	Songs.insert(nameDBObject);
	System.out.println("SongWriter(s).Name");
	String SongWriter = bufferedReader.readLine();
	BasicDBList SongWriterDBList = new BasicDBList();
	DBObject SongWriterDBObject = null;
	
	nameDBObject.put("SongWriter(s).Name", SongWriterDBList.toArray());
	Songs.insert(nameDBObject);
	
	
}
//Insert Song Line
private static void insertJSON(BufferedReader bufferedReader, DBCollection Songs) throws IOException {
	System.out.println("JSON Line: ");
	Songs.insert((DBObject)JSON.parse(bufferedReader.readLine()));
}

//Find All in Database
private static void findAll(DBCollection Songs) {
	DBCursor dbCursor = Songs.find();
	while(dbCursor.hasNext())System.out.println(dbCursor.next());
	System.out.println("Documents in your database");
	System.out.println(Songs.getCount());
}

//Find Producer in Database
private static void findProducer(BufferedReader bufferedReader, DBCollection Songs) throws IOException {
	
	BasicDBObject allQuery = new BasicDBObject();
	BasicDBObject fields = new BasicDBObject();
	fields.put("Producer.Name", 1);
	
	DBCursor cursor = Songs.find(allQuery, fields);
	while (cursor.hasNext()) {
		System.out.println(cursor.next());
	}

}



//find one
private static void find(BufferedReader bufferedReader, DBCollection Songs) throws IOException {
	System.out.println("Which song would you like to find? ");
	BasicDBObject doc = new BasicDBObject("Song Title", bufferedReader.readLine());
	Cursor cursor=Songs.find(doc);
	while(cursor.hasNext()) {
		System.out.println(cursor.next());
	
	}
}

//find by Artist
private static void findByArtistName(BufferedReader bufferedReader, DBCollection Songs) throws IOException {
	System.out.println("Which Artist song would you like to find? ");
	BasicDBObject doc = new BasicDBObject("ArtistName", bufferedReader.readLine());
	Cursor cursor=Songs.find(doc);
	while(cursor.hasNext()) {
		System.out.println(cursor.next());
	
	}
}

//find by Genre
private static void findByGenre(BufferedReader bufferedReader, DBCollection Songs) throws IOException {
	System.out.println("Which genre of song would you like to find? ");
	BasicDBObject doc = new BasicDBObject("Genre", bufferedReader.readLine());
	Cursor cursor=Songs.find(doc);
	while(cursor.hasNext()) {
		System.out.println(cursor.next());
	
	}
}


//find by Date
private static void findByDate(BufferedReader bufferedReader, DBCollection Songs) throws IOException {
	System.out.println("Which song release date would you like to find? ");
	BasicDBObject doc = new BasicDBObject("Date Release", bufferedReader.readLine());
	Cursor cursor=Songs.find(doc);
	while(cursor.hasNext()) {
		System.out.println(cursor.next());
	
	}
}


//find by Album
private static void findByAlbum(BufferedReader bufferedReader, DBCollection Songs) throws IOException {
	System.out.println("Which song album would you like to find? ");
	BasicDBObject doc = new BasicDBObject("Album", bufferedReader.readLine());
	Cursor cursor=Songs.find(doc);
	while(cursor.hasNext()) {
		System.out.println(cursor.next());
	
	}
}

//find by Producer
private static void findByProducer(BufferedReader bufferedReader, DBCollection Songs) throws IOException {
	System.out.println("What would you like to find? ");
	BasicDBObject doc = new BasicDBObject("Producer.Name", bufferedReader.readLine());
	Cursor cursor=Songs.find(doc);
	while(cursor.hasNext()) {
		System.out.println(cursor.next());
	
	}
	
	
}

//find by SongWriter
private static void findBySongWriter(BufferedReader bufferedReader, DBCollection Songs) throws IOException {
	System.out.println("Which song produce by would you like to find? ");
	BasicDBObject doc = new BasicDBObject("SongWriter(s).Name", bufferedReader.readLine());
	Cursor cursor=Songs.find(doc);
	while(cursor.hasNext()) {
		System.out.println(cursor.next());
	
	}
}

}