package com.example.block13mongodb;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoDBConfig {

    @Bean
    public MongoTemplate mongoTemplate() {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient(), "myMongoDB");
        return mongoTemplate;
    }


    @Bean
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb://127.0.0.1:27017");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .credential(MongoCredential.createCredential("mdbUser", "myMongoDB", "cp".toCharArray()))
                .build();

        return MongoClients.create(settings);
    }
}

