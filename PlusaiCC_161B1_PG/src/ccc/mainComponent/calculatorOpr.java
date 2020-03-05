package ccc.mainComponent;

public class calculatorOpr {

	public static double firstNum;
	public static double secondNum;
	public static double result;
	public static String operator;
	public static String answer;
	
	public static void calculate() {
		// TODO Auto-generated method stub
		if(operator == "+") {
			result = firstNum + secondNum;
			//answer = String.format("", result);
			answer = String.valueOf(result);
		}else if(operator == "-") {
			result = firstNum - secondNum;
			//answer = String.format("", result);
			answer = String.valueOf(result);
		}else if(operator == "*") {
		result = firstNum * secondNum;
		//answer = String.format("", result);
		answer = String.valueOf(result);
		}else if(operator == "/") {
			result = firstNum / secondNum;
			//answer = String.format("", result);
			answer = String.valueOf(result);
		}
		
		//if(Double.parseDouble(answer)%10.00==1) {
			//answer = String.valueOf(Integer.valueOf(answer));
		//}
	}
}
