package samples.dynamodb;

import samples.dynamodb.model.User;

public class CreateItem extends DynamoDBSample {

	public void run() {
		createUser(1L, "Eduardo", true, 30);
		createUser(2L, "Pedro", true, 27);
		createUser(3L, "Carlos", false, 32);
		createUser(4L, "José", true, 33);
	}

	private void createUser(Long id, String name, Boolean active, Integer age) {
		System.out.println("Creating user!");
		User user = new User(id, name, active, age);
		mapper.save(user);
		System.out.println("User created!");
	}

	public static void main(String[] args) {
		new CreateItem().run();
	}

}
