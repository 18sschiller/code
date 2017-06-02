import java.util.Stack;
/**
 * This class can take an input equation and solve it using the order of operations.
 * You can use any real number, along with + , - , * , / , % , ) , ( , pi , ! , e, abs(), sqrt(), cbrt(), sin(), cos(), tan(), sec(), csc(), and cot().
 * The getAnswer method will take the equation as a parameter and return the answer as a double.
 * @author 18sschiller
 *
 */
public class PEMDASolver 
{
	public PEMDASolver(){}
	
	public static void main(String[] args) 
	{
		PEMDASgui gj = new PEMDASgui();
		// with postfix notation
		String o = "5 2 + 8 5 - *";
		System.out.println(postFix(o));
		/*
		 * You can write out equations here.
		 */
		
		// without postfix notation
		String qw = "cos( pi )";
		System.out.println(getAnswer(qw));
		String a = "3 * 4 ^ 2 + e";
		System.out.println(getAnswer(a));
		String c = "7 * ( 9 - 10 / 5 )";
		System.out.println(getAnswer(c));
		String e = "12 - 5 - 3";
		System.out.println(getAnswer(e));
		String g = "( ( ( ( 4 * 8 ) + ( 7 / 72 ) + 4 ) + 8 ) * 8 )";
		System.out.println(getAnswer(g));
		String hello = "( ( ( ( ( pi ) ) ) ) )";
		System.out.println(getAnswer(hello));
		String q = "4 ! * ( 3 + 4 ) - 5 * ( 7 * 10 ^ 2 )";
		System.out.println(getAnswer(q));
	}
	
	/**
	 * Solves the same thing as the other method, but uses Post-fix notation, which makes it a lot more simple.
	 * @param eqtn
	 * @return answer
	 */
	public static double postFix( String eqtn)
	{
		System.out.println("\n" + eqtn); // prints equation
		Stack<Double> operands = new Stack<Double>(); // instantiates a new stack of operands

		String[] xl = eqtn.split(" "); // makes a string array from the equation, separated by spaces
		for (int i = 0; i < xl.length; i++) { // runs to length of the equation
			try{operands.push(Double.parseDouble(xl[i]));} // tries to parse a double, and it will work if it is a number
			catch(NumberFormatException exception){ // if it is an operator...
				if(operands.size() < 2) System.out.println("Invalid Input: Insufficient values in expression."); // if it is less than two, it doesn't work
				else operands = doOperation(operands.pop(), operands.pop(), xl[i], operands); // does the operation
			}
		}
		if(operands.size() > 1){ // if there is more than one number left, it was done incorrectly
			System.out.println("Invalid Input: Too many values in expression."); // tells you it was wrong
			return -1; // returns fake number
		}
		else return (Math.round(operands.peek() * 100))/100; // otherwise, returns answer
	}
	
	/**
	 * Takes in an equation and gives user the answer.
	 * PRECONDITION: equation must be given in a String with a space in between every operator and operand.
	 * POSTCONDITION: answer will be a double with no more than three decimal places.
	 * @param eqtn
	 * @return answer
	 */
	public static double getAnswer( String eqtn)
	{
		System.out.println("\n" + eqtn); // prints equation
		double r = Math.round(stackify(eqtn, 0) * 100);
		return r / 100; // rounds it and runs rest of program
	}
	
	/**
	 * Takes the equation and separates it into a stack of operators and operands. 
	 * It will then solve the equation based on that, and return the answer.
	 */
	private static double stackify(String equation, int openParen)
	{
		Stack<String> operators = new Stack<String>(); // instantiates a new stack of operators
		Stack<Double> operands = new Stack<Double>(); // instantiates a new stack of operands

		String[] xl = equation.split(" "); // makes a string array from the equation, separated by spaces
		for (int i = 0; i < xl.length; i++) { // runs to however many strings are in the array
				if(xl[i].equals("pi")) xl[i] = "3.1415926";
				if(xl[i].equals("e")) xl[i] = "2.71828";
				if(xl[i].equals("!")) // if it is a factorial
					operands.push((double)fact(operands.pop().intValue())); // complete operation immediately for factorial
				else{ // if it is anything else
					try{operands.push(Double.parseDouble(xl[i]));} // tries to parse a double, and it will work if it is a number
					catch(NumberFormatException exception){ // if it is not a number, it is an operator
						addOperator(xl[i], operators, operands, openParen); // adds the operator using the addOperator method
						if(xl[i].contains("(")){openParen++;} // if it is an open parenthesis, it will add more weight to following operators.
					}
				}
		}
		while(!operators.empty() && !operands.empty()) // once all of the operators are stacked, runs until they are empty
			operands = doOperation(operands.pop(), operands.pop(), operators.pop(), operands); // does all of the operations
		return operands.peek(); // sets the return value to the only value left in the operands stack
	}
	
	/**
	 * Adds the operator to the operator stack, and if it is not as high in the order of operations,
	 * it will solve the parts of the equation before it until it is more important than all of the 
	 * operations below it.
	 * @param str
	 */
	private static void addOperator( String str, Stack<String> operators, Stack<Double> operands, int openParen)
	{
		if(str.equals(")")){ // if the operator is a )...
			while(!operators.peek().contains("(")) // while the next one down is not a (...
				operands = doOperation(operands.pop(), operands.pop(), operators.pop(), operands); // runs the doOperation method to perform operation
			doModifier(operators.pop(), operands.pop(), operands);
			openParen--; // removes the supplemental weight given to operands within the parentheses
		}
		else if(operators.empty() || compareOps(str, operators.peek(), openParen) == -1 || operators.peek().contains("(") || str.contains("("))
			operators.push(str); // if it is the beginning or it is of higher precedence or if the last operator was a ( or if
		else{	// it is a ( and it also has to not be a ) because that is illegal, then it will put it into the operator column
			operands = doOperation(operands.pop(), operands.pop(), operators.pop(), operands); // perform the operation for the last term to solve the last order
			addOperator(str, operators, operands, openParen); // recursively run the method again to see if it works.
		}
	}
	
	/**
	 * Checks to see if the parentheses were modified using any of the operations.
	 * Operations are:
	 * Absolute Value - abs(
	 * Square Root - sqrt(
	 * Cube Root - cbrt(
	 * Cosine - cos(
	 * Sine - sin(
	 * Tangent - tan(
	 * Secent - sec(
	 * Cosecent - csc(
	 * Cotangent - cot(
	 * @param operator
	 * @param number
	 * @param operands
	 * @return
	 */
	private static Stack<Double> doModifier( String operator, double number, Stack<Double> operands)
	{
		if( operator.equals("(")) operands.push(number);
		else if( operator.equals("abs("))  operands.push(Math.abs(number));
		else if( operator.equals("sqrt(")) operands.push(Math.sqrt(number));
		else if( operator.equals("cbrt(")) operands.push(Math.cbrt(number));
		else if( operator.equals("cos("))  operands.push(Math.cos(number));
		else if( operator.equals("sin("))  operands.push(Math.sin(number));
		else if( operator.equals("tan("))  operands.push(Math.tan(number));
		else if( operator.equals("sec("))  operands.push(1/(Math.cos(number)));
		else if( operator.equals("csc("))  operands.push(1/(Math.sin(number)));
		else if( operator.equals("cot("))  operands.push(1/(Math.tan(number)));
		else							   operands.push(number);
		return operands;
	}
	
	/**
	 * Performs the math operation that is represented by the operand in the original String
	 * @param numOne
	 * @param numTwo
	 * @param operate
	 */
	private static Stack<Double> doOperation( double numOne, double numTwo, String operate, Stack<Double> operands)
	{
		if(operate.equals("^")) // if it is a power sign
			operands.push((Math.pow(numTwo, numOne))); // uses the power
		else if(operate.equals("*")) // if it is a multiplication
			operands.push(numOne*numTwo); // multiplies the numbers
		else if(operate.equals("/")) // if it is a division
			operands.push(numTwo/numOne); // divides the two numbers
		else if(operate.equals("+")) // if it is a plus sign
			operands.push(numOne+numTwo); // adds the numbers
		else if(operate.equals("-")) // if it is a subtraction symbol
			operands.push(numTwo-numOne); // subtracts the numbers
		else if(operate.equals("%")) // if it's a mod symbol
			operands.push(numTwo % numOne); // it takes the mod
		return operands; // returns the stack
	}
	
	private static int fact(int n)
	{
		if( n <= 1)
			return 1;
		return fact(n-1) * n;
	}
	

	/**
	 * Compares to operators and returns the importance of them in comparison to each other.
	 * If it returns -1, the operator being added is of greater precedence than the operator on the stack, and it can be added.
	 * If it returns 0, the operator has an equivalent precedence.
	 * If it returns 1, the operator being added is of lower precedence than the operator on the stack, and it needs to go under.
	 * @param str1
	 * @param str2
	 * @return comparison
	 */
	private static int compareOps(String str1, String str2, int openParen)
	{
		int one = assignValue(str1, openParen); // gets the value of the first operator
		int two = assignValue(str2, openParen); // gets value of the second operator
		if(one > two) // if the first is higher priority than the second
			return -1; // returns -1
		else if(one == two) // if they are of equal priority
			return 0; // returns 0
		else // if the first is lower priority than the second
			return 1; // returns 1
	}
	
	/**
	 * Assigns a value to the operator to prioritize them in the order of operations.
	 * 
	 * @param str
	 * @return value of int
	 */
	private static int assignValue( String str, int openParen)
	{
		int u = 0; // makes it a variable instead of returning 
		if(str.equals(")")) // so it can be modified at the end
			u =  10; // if it is a closed parenthesis, lowest priority
		else if(str.equals("+") || str.equals("-")) 
			u =  20; // if it is a + or -, equal priority
		else if(str.equals("*") || str.equals("%") || str.equals("/"))
			u =  30; // if it is * or / or mod, higher but equal priority
		else if(str.equals("^")) // when it is power sign
			u =  40; // power is higher on the order of operations
		else if(str.equals("!")) // when it is factorial
			u =  45; // factorial is the second highest in the order
		else if(str.contains("("))
			return 50 + openParen; // if it is (, it is the highest priority
		return u + (100 * openParen); // returns this if it is within parenthesis
	}

}
