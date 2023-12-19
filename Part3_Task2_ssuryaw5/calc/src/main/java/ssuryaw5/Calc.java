package ssuryaw5;

import org.springframework.web.client.RestTemplate;

public class Calc {
	private static int calculatorValue = 10;

	public static String formatURL(char operator, String operand) {
		String port;
		String controller;

		switch (operator) {
			case '*':
				port = "8082";
				controller = "multiply";
				break;
			case '/':
				port = "8083";
				controller = "divide";
				break;
			case '=':
				port = "8081";
				controller = "assign";
				break;
			default:
				port = "";
				controller = "";
		}

		String result =  "http://localhost:" + port + "/calculate/" + controller + "?operand=" + operand;
		return result;
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Usage: mvn spring-boot:run -Dspring-boot.run.arguments=operator,operand");
			System.exit(-1);
		}

		char operator = args[0].charAt(0);
		String operand = args[1];

		System.out.println(getResultByRemote(operator, operand));

	}

	public static String getResultByRemote(char operator, String operand) {
		String operatorurl = Calc.formatURL(operator, operand);

		try {
			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.getForObject(operatorurl, String.class);
			return ("Result: " + result);
		} catch (Exception e) {
			return ("Error: " + e.getMessage());
		}
	}
}