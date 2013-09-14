package samples.dynamodb;

import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest;

public class DeleteTable extends DynamoDBSample {

	public void run() {
		System.out.println("Deleting table user!");
		DeleteTableRequest request = new DeleteTableRequest();
		request.setTableName("user");
		client.deleteTable(request);
		System.out.println("Table users deleted!");
	}

	public static void main(String[] args) {
		new DeleteTable().run();
	}

}
