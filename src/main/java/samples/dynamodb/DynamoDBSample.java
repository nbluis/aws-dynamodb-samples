package samples.dynamodb;

import java.io.IOException;
import java.io.InputStream;

import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public abstract class DynamoDBSample {

	protected AmazonDynamoDBAsyncClient client;
	protected DynamoDBMapper mapper;

	public DynamoDBSample() {
		try {
			InputStream credentialsFile = this.getClass().getClassLoader().getResourceAsStream("aws.properties");
			PropertiesCredentials credentials = new PropertiesCredentials(credentialsFile);
			client = new AmazonDynamoDBAsyncClient(credentials);
			mapper = new DynamoDBMapper(client);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
