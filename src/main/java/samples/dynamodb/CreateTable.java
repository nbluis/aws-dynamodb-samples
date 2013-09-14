package samples.dynamodb;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

public class CreateTable extends DynamoDBSample {

	public void run() {
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

		client.createTable(request);
		System.out.println("Table users created!");
	}

	public static void main(String[] args) {
		new CreateTable().run();
	}

}
