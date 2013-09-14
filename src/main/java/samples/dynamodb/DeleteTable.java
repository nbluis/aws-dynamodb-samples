package samples.dynamodb;

import com.amazonaws.services.dynamodbv2.model.DeleteTableRequest;

public class DeleteTable extends DynamoDBSample {

	public void run() {
		System.out.println("Deleting table user!");
		DeleteTableRequest request = new DeleteTableRequest();
		request.setTableName("user");
		deleteTableWithRetries(request, 10);
		System.out.println("Table users deleted!");
	}

	private void deleteTableWithRetries(DeleteTableRequest request, int retries) {
		for (int i = 1; i < retries; i++) {
			try {
				client.deleteTable(request);
				return;
			} catch (Exception e) {
				System.out.println(String.format("Table %s not deletable yet, try %d of %d!", request.getTableName(), i, retries));
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e1) {
					//ignore
				}
			}
		}

	}

	public static void main(String[] args) {
		new DeleteTable().run();
	}

}
