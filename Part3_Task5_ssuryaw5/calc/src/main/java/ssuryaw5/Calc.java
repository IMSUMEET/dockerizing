package ssuryaw5;

import org.springframework.web.client.RestTemplate;

public class Calc {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: mvn spring-boot:run -Dspring-boot.run.arguments=operator,operand");
			System.exit(-1);
		}

		char operator = args[0].charAt(0);
		String operand = args[1];

		System.out.println(operator + " " + operand);
		System.out.println(getResultByRemote(operator, operand));

	}

	public static String getResultByRemote(char operator, String operand) {
		String operatorurl = "http://localhost:3000/calculate?operator=" + operator + "&operand=" + operand;

		try {
			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.getForObject(operatorurl, String.class);
			return ("Result: " + result);
		} catch (Exception e) {
			return ("Error: " + e.getMessage());
		}
	}
}