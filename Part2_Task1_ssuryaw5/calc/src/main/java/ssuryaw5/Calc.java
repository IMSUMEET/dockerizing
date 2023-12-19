package ssuryaw5;

public class Calc {
	private static int calculatorValue = 10;


	public static void main(String[] args) {
		if (args.length != 2) {
			System.exit(-1);
		}

		char operator = args[0].charAt(0);
		String operand = args[1];

		try {
			int intValue = Integer.parseInt(operand);
			int result = calculate(operator, intValue);
			System.out.println("Result: " + result);
		} catch (NumberFormatException e) {
			System.out.println("Invalid integer input: " + operand);
		} catch (ArithmeticException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public static void reset(){
		calculatorValue = 10;
	}

	public static int calculate(char operator, int operand) {
		switch(operator){
			case '*':
				if(checkOverflow(operand)){
					throw new ArithmeticException("OverFlowing result");
				}
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