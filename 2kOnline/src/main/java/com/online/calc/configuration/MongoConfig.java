package com.online.calc.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.WriteResultChecking;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {


	    @Value(value="heroku_fvd5dk81")
	    private String databaseName;

	    @Value(value="ds241308.mlab.com:41308")
	    private String host;
	    
	    @Value(value="pangolier")
	    private String username;
	    
	    @Value(value="ejbrealobre")
	    private String password;
	    
	    @Value(value="heroku_fvd5dk81")
	    private String database;

	    @Value(value="41308")
	    private int port;


	    @Override
	    public MongoClient mongoClient() {
	    	//ServerAddress serverAddress = new ServerAddress("ds241308.mlab.com", 41308);
	    	//MongoCredential mongoCredential = MongoCredential.createCredential("pangolier", "heroku_fvd5dk81", "ejbrealobre".toCharArray());
	    	MongoClientURI mongoUri = new MongoClientURI("mongodb://neki:random234435@ds241308.mlab.com:41308/heroku_fvd5dk81?retryWrites=false");
	    	//MongoClient mongoClient = new MongoClient(serverAddress, Arrays.asList(mongoCredential));
//	    	System.out.println(mongoUri);
//	        return new MongoClient(mongoUri);
	    	//MongoDatabase mongoDB = mongoClient.getDatabase("demodb");
	    	return new MongoClient(mongoUri);
	    }

	    @Override
	    protected String getDatabaseName() {
	        return databaseName;
	    }

	    @Bean
	    public MongoTemplate mongoTemplate() throws Exception {
	        MongoTemplate myTemp = new MongoTemplate(mongoDbFactory(), mappingMongoConverter());
	        myTemp.setWriteResultChecking(WriteResultChecking.EXCEPTION);
	        return myTemp;
	    }
}
