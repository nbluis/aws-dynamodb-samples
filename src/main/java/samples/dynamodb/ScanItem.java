package samples.dynamodb;

import static com.amazonaws.services.dynamodbv2.model.ComparisonOperator.EQ;

import java.util.Iterator;

import samples.dynamodb.model.User;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.Condition;

public class ScanItem extends DynamoDBSample {

	public void run() {
		System.out.println("Scanning user Eduardo!");

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		scanExpression.addFilterCondition("name", new Condition().withComparisonOperator(EQ).withAttributeValueList(new AttributeValue().withS("Eduardo")));
		scanExpression.addFilterCondition("age", new Condition().withComparisonOperator(EQ).withAttributeValueList(new AttributeValue().withN("30")));
		scanExpression.addFilterCondition("active", new Condition().withComparisonOperator(EQ).withAttributeValueList(new AttributeValue().withN("1")));

		PaginatedScanList<User> list = mapper.scan(User.class, scanExpression);

		Iterator<User> iter = list.iterator();

		if (!iter.hasNext())
			throw new RuntimeException("User not found!");

		while (iter.hasNext()) {
			User row = iter.next();
			System.out.println(row.getId());
			System.out.println(row.getName());
		}

		System.out.println("Ending scan!");
	}

	public static void main(String[] args) {
		new ScanItem().run();
	}
}
