package samples.dynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "user")
public class User {

	private Long id;
	private String name;
	private Boolean active;
	private Integer age;

	public User() {}

	public User(Long id) {
		this();
		this.id = id;
	}

	public User(Long id, String name) {
		this(id);
		this.name = name;
	}

	public User(Long id, String name, Boolean active, Integer age) {
		this(id, name);
		this.active = active;
		this.age = age;
	}

	@DynamoDBHashKey(attributeName = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@DynamoDBRangeKey(attributeName = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@DynamoDBIndexRangeKey(attributeName = "age", localSecondaryIndexName = "age_index")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
