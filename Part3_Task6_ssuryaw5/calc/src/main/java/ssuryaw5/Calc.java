package ssuryaw5;

import org.springframework.web.client.RestTemplate;

public class Calc {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: mvn spring-boot:run -Dspring-boot.run.arguments=query");
			System.exit(-1);
		}

		String query = args[0];

		System.out.println(getResultByRemote(query));

	}

	public static String getResultByRemote(String query) {
		String operatorurl = "http://localhost:3000/calculate?query=" + query;

		try {
			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.getForObject(operatorurl, String.class);
			return ("Result: " + result);
		} catch (Exception e) {
			return ("Error: " + e.getMessage());
		}
	}
}