package social.teste.oauth2.server.mongo.configuracao;

import java.util.Collection;
import java.util.Collections;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackages = "social.teste.oauth2.server.mongo.repository")
public class MongoConfig extends AbstractMongoClientConfiguration {
 
    @Override
    protected String getDatabaseName() {
        return "oauth2";
    }
 
    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString("mongodb://192.168.0.16:27017");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build();
        
        return MongoClients.create(mongoClientSettings);
    }
 
    @Override
    public Collection getMappingBasePackages() {
        return Collections.singleton("social.teste.oauth2.server.mongo.entidade");
    }
}