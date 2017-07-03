package cn.demo.service0.config;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@ConfigurationProperties(prefix = "spring.data.mongodb")
@RefreshScope
public class Mongo3Properties {
	
	//private String url = "mongodb://root:root@localhost:27017/test";
	private String url;
	private String database = "admin";
	private String gridFsDatabase;

	public String getGridFsDatabase() {
		return gridFsDatabase;
	}

	public void setGridFsDatabase(String gridFsDatabase) {
		this.gridFsDatabase = gridFsDatabase;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getMongoClientDatabase() {
		return new MongoClientURI(this.url).getDatabase();
	}

	public MongoClient createMongoClient(MongoClientOptions options) throws UnknownHostException {
		try {
			System.out.println("----查看注入消息：" + this.getUrl() + " " + this.getDatabase());
			MongoClientURI clientURI = new MongoClientURI(this.url, builder(options));
			if (options == null) {
				options = MongoClientOptions.builder().build();
			}
			List<String> hosts = clientURI.getHosts();
			System.out.println("当前所有的主机和端口数据：" + hosts.toString());
			List<ServerAddress> serverAddressList = new ArrayList<ServerAddress>();
			for (String host : hosts) {
				String[] hostArr = host.split(":");
				serverAddressList.add(new ServerAddress(hostArr[0], Integer.valueOf(hostArr[1])));// 主机和端口号
			}
			String username = clientURI.getUsername();
			char[] password = clientURI.getPassword();
			System.out.println("clientURI "+clientURI);
			if (username != null && password != null) {
				List<MongoCredential> credentials = Arrays
						.asList(MongoCredential.createScramSha1Credential(username, database, password));// 启用Mongodb3的默认的登录验证
				return new MongoClient(serverAddressList, credentials, options);
			}
			return new MongoClient(serverAddressList, options);
		} finally {
		}
	}

	private Builder builder(MongoClientOptions options) {
		Builder builder = MongoClientOptions.builder();
		if (options != null) {
			builder = MongoClientOptions.builder(options);
		}
		return builder;
	}
}
