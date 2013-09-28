package samples.dynamodb;

import samples.dynamodb.model.User;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;

public class UpdateItem extends DynamoDBSample {

	public void run() {
		String newName = "Jo‹o";
		User user = new User(4L, newName);
		mapper.save(user);

		DynamoDBQueryExpression<User> query = new DynamoDBQueryExpression<User>();
		query.setHashKeyValues(user);
		PaginatedQueryList<User> result = mapper.query(User.class, query);
		User updatedUser = result.iterator().next();
		System.out.println(updatedUser.getName());
	}

	public static void main(String[] args) {
		new UpdateItem().run();
	}

}
