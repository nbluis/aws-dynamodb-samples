package samples.dynamodb;

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public abstract class DynamoDBSample {

	protected AmazonDynamoDBAsyncClient client;
	protected DynamoDBMapper mapper;

	public DynamoDBSample() {
		Region region = Region.getRegion(Regions.US_EAST_1);
		ClasspathPropertiesFileCredentialsProvider credentials = new ClasspathPropertiesFileCredentialsProvider("aws.properties");
		client = (AmazonDynamoDBAsyncClient) region.createClient(AmazonDynamoDBAsyncClient.class, credentials, null);
		mapper = new DynamoDBMapper(client);
	}
}
