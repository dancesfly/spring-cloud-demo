package cn.demo.service0.config;

import java.net.UnknownHostException;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;

@Configuration
@ConditionalOnClass(MongoClient.class)
@EnableConfigurationProperties(Mongo3Properties.class)
@ConditionalOnMissingBean(type = "org.springframework.data.mongodb.MongoDbFactory")
public class Mongo3AutoConfiguration {
	@Autowired
	private Mongo3Properties properties;

	@Autowired(required=false)
	private MongoClientOptions options;

	private MongoClient mongoClient;

	@PreDestroy
	public void close() {
		if (this.mongoClient != null) {
			System.out.println("++++++++++++close");
			this.mongoClient.close();
		}
	}

	@Bean
	@ConditionalOnMissingBean //创建MongoClient
	public MongoClient mongo() throws UnknownHostException {
		System.out.println("++++++++++++++++++MongoClientOptions为："+this.options);
		this.mongoClient = this.properties.createMongoClient(this.options);
		System.out.println("---------------------mongoClient为："+this.mongoClient);
		return this.mongoClient;
	}
}
