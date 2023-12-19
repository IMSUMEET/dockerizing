package ssuryaw5;

import org.springframework.web.client.RestTemplate;

public class Calc {
	private static int calculatorValue = 10;
	private static String url = "http://localhost:8080/calculate";

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("Usage: mvn spring-boot:run -Dspring-boot.run.arguments=operator,operand,remote");
			System.exit(-1);
		}

		char operator = args[0].charAt(0);
		String operand = args[1];
		String remote = args[2];

		boolean useRemote;
        useRemote = remote.equals("1");

		if(useRemote){
			System.out.println(getResultByRemote(operator, operand));
		} else{
			System.out.println(getResult(operator, operand));
		}
	}

	public static String getResult(char operator, String operand){
		try {
			int intValue = Integer.parseInt(operand);
			int result = Calc.calculate(operator, intValue);
			return ("Result: " + result);
		} catch (NumberFormatException e) {
			return ("Invalid integer input: " + operand);
		} catch (ArithmeticException e) {
			return ("Error: " + e.getMessage());
		}
	}

	public static String getResultByRemote(char operator, String operand){
		String operatorurl;
		switch(operator){
			case '*':
				operatorurl = Calc.url + "/multiply?operand=" + operand;
				break;
			case '/':
				if(Integer.parseInt(operand) == 0){
					throw new ArithmeticException("Division by zero not allowed");
				}else {
					operatorurl = Calc.url + "/divide?operand=" + operand;
				}
				break;
			case '=':
				operatorurl = Calc.url + "/assign?operand=" + operand;
				break;
			default:
				return ("Error: Invalid Operator " + operator);
		}

		try {
			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.getForObject(operatorurl, String.class);
			return ("Result: " + result);
		} catch(Exception e) {
			return ("Error: " + e.getMessage());
		}
	}

	public static void reset(){
		calculatorValue = 10;
	}

	public static int calculate(char operator, int operand) {
		switch(operator){
			case '*':
				if(checkOverflow(operand)) throw new ArithmeticException("OverFlowing result");
				calculatorValue *= operand;
				break;
			case '/':
				if(operand == 0){
					throw new ArithmeticException("Division by zero not allowed");
				}else{
					calculatorValue /= operand;
				}
				break;
			case '=':
				calculatorValue = operand;
				break;
			default:
				throw new IllegalArgumentException("Invalid Operator");
		}
		return calculatorValue;
	}

	public static boolean checkOverflow(long value){
		value *= calculatorValue;
        return value >= Integer.MAX_VALUE || value < Integer.MIN_VALUE;
    }
}