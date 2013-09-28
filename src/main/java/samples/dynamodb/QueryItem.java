package samples.dynamodb;

import java.util.Iterator;

import samples.dynamodb.model.User;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;

public class QueryItem extends DynamoDBSample {

	public void run() {
		System.out.println("Querying user Eduardo!");

		DynamoDBQueryExpression<User> query = new DynamoDBQueryExpression<User>();
		query.setHashKeyValues(new User(1L, "Eduardo"));

		PaginatedQueryList<User> list = mapper.query(User.class, query);
		Iterator<User> iter = list.iterator();

		if (!iter.hasNext())
			throw new RuntimeException("User not found!");

		while (iter.hasNext()) {
			User row = iter.next();
			System.out.println(row.getId());
			System.out.println(row.getName());
		}

		System.out.println("Ending Query!");
	}

	public static void main(String[] args) {
		new QueryItem().run();
	}
}
