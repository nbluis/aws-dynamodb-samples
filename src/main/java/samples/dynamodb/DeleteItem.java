package samples.dynamodb;

import samples.dynamodb.model.User;

public class DeleteItem extends DynamoDBSample {

	public void run() {
		System.out.println("Deleting user Eduardo!");
		User user = new User(1L, "Eduardo");
		mapper.delete(user);
		System.out.println("User deleted!");
	}

	public static void main(String[] args) {
		new DeleteItem().run();
	}
}
