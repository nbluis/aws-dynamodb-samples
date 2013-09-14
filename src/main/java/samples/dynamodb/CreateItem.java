package samples.dynamodb;

import samples.dynamodb.model.User;

public class CreateItem extends DynamoDBSample {

	public void run() {
		User user = new User(1L, "Eduardo");
		mapper.save(user);
		System.out.println("User created!");
	}

	public static void main(String[] args) {
		new CreateItem().run();
	}

}
