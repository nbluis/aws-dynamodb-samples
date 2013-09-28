package samples.dynamodb;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import samples.dynamodb.model.User;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;

public class QueryItem extends DynamoDBSample {

	public void run() {
		System.out.println("Querying users");

		queryByHashKey();

		queryWithIndex();

		System.out.println("Ending Query!");
	}

	private void queryByHashKey() {
		DynamoDBQueryExpression<User> query = new DynamoDBQueryExpression<User>();
		query.setHashKeyValues(new User(1L));

		PaginatedQueryList<User> list = mapper.query(User.class, query);

		verifyUsers(list.iterator());
	}

	private void queryWithIndex() {
		DynamoDBQueryExpression<User> query = new DynamoDBQueryExpression<User>();
		query.setIndexName("age_index");
		query.setHashKeyValues(new User(1L));

		Map<String, Condition> conditions = new HashMap<String, Condition>();
		conditions.put("age", new Condition().withComparisonOperator(ComparisonOperator.EQ).withAttributeValueList(new AttributeValue().withN("30")));
		query.setRangeKeyConditions(conditions);

		PaginatedQueryList<User> list = mapper.query(User.class, query);

		verifyUsers(list.iterator());
	}

	private void verifyUsers(Iterator<User> iter) {
		if (!iter.hasNext())
			throw new RuntimeException("No user found!");

		while (iter.hasNext()) {
			User row = iter.next();
			System.out.println(row.getId());
			System.out.println(row.getName());
			System.out.println(row.getAge());
			System.out.println(row.getActive());
		}
	}

	public static void main(String[] args) {
		new QueryItem().run();
	}
}
