package samples.dynamodb;

import java.util.ArrayList;
import java.util.List;

import samples.dynamodb.model.User;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

public class CreateTable extends DynamoDBSample {

	public void run() {
		System.out.println("Creating table user!");
		CreateTableRequest request = new CreateTableRequest();
		request.setTableName("user");

		ProvisionedThroughput provisionedThroughput = new ProvisionedThroughput();
		provisionedThroughput.setReadCapacityUnits(1L);
		provisionedThroughput.setWriteCapacityUnits(1L);
		request.setProvisionedThroughput(provisionedThroughput);

		List<AttributeDefinition> attributes = new ArrayList<AttributeDefinition>();
		attributes.add(new AttributeDefinition("id", ScalarAttributeType.N));
		attributes.add(new AttributeDefinition("name", ScalarAttributeType.S));
		request.setAttributeDefinitions(attributes);

		List<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
		keySchema.add(new KeySchemaElement("id", KeyType.HASH));
		keySchema.add(new KeySchemaElement("name", KeyType.RANGE));
		request.setKeySchema(keySchema);

		createTableWithRetries(request, 10);
		System.out.println("Table user created!");

		waitForTableAvailable(User.class, 10);

	}

	private void waitForTableAvailable(Class<?> clazz, int retries) {
		String tableName = clazz.getAnnotation(DynamoDBTable.class).tableName();

		System.out.println(String.format("Waiting for table %s to be available!", tableName));
		for (int i = 1; i < retries; i++) {
			try {
				mapper.load(User.class, 0L, "range");
				System.out.println(String.format("Table %s is now available!", tableName));
				return;
			} catch (Exception e) {
				System.out.println(String.format("Table %s not available yet, try %d of %d!", tableName, i, retries));
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					//ignore
				}
			}
		}
	}

	private void createTableWithRetries(CreateTableRequest request, int retries) {
		for (int i = 1; i < retries; i++) {
			try {
				client.createTable(request);
				return;
			} catch (Exception e) {
				System.out.println(String.format("Failed to create table %s, try %d of %d ", request.getTableName(), i, retries));
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					//ignore
				}
			}
		}
	}

	public static void main(String[] args) {
		new CreateTable().run();
	}

}
