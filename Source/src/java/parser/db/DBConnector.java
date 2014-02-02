/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parser.db;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import java.io.IOException;
import java.net.UnknownHostException;
import parser.db.conf.ConfigurationFile;

/**
 *
 * @author mohamed.azouz
 */
public class DBConnector {

    private ConfigurationFile configurationFile;
    private MongoClient mongoClient;
    private DB db;
    private static DBConnector instance = null;

    private DBConnector(ConfigurationFile configurationFile) throws UnknownHostException, IOException {
        this.configurationFile = configurationFile;
        this.connect();
    }

    public static DBConnector getInstance(ConfigurationFile configurationFile) throws UnknownHostException, IOException {
        if (instance == null) {
            instance = new DBConnector(configurationFile);
        }
        return instance;
    }

    private void connect() throws UnknownHostException {
        //MongoClient mongoClient = new MongoClient("192.168.52.131", 27017);
        //MongoCredential credential = MongoCredential.createMongoCRCredential(configurationFile.getUsername(), configurationFile.getDatabaseName(), configurationFile.getPassword().toCharArray());

        //mongoClient = new MongoClient(new ServerAddress(configurationFile.getHost(), configurationFile.getPort()), Arrays.asList(credential));
        mongoClient = new MongoClient(configurationFile.getHost(),configurationFile.getPort());
        db = mongoClient.getDB(configurationFile.getDatabaseName());
    }

    public void insertNewTrack(JsonObject trackJson) {
        DBCollection coll = db.getCollection("tracks");
        Gson gson = new Gson();
        String json = gson.toJson(trackJson);
        System.out.println(json);
        DBObject dbObject = (DBObject) JSON.parse(json);
        coll.insert(dbObject);
    }

    private void disonnect() {
        mongoClient.close();
    }

    /**
     * @return the db
     */
    public DB getDb() {
        return db;
    }
}
