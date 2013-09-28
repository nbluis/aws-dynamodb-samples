package samples.dynamodb;

public class AllSamples {

	private void run() {
		new CreateTable().run();
		new CreateItem().run();
		new QueryItem().run();
		new ScanItem().run();
		new DeleteItem().run();
		new DeleteTable().run();
	}

	public static void main(String[] args) {
		new AllSamples().run();
	}

}
