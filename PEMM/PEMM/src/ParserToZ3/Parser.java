package ParserToZ3;

import java.util.*;

public class Parser {
	
	public static String parse(String line)
	{
		//String result=infixToPrefix(line);
		String result = infixToPrefix(line.replaceAll("\\s+",""));
		result = result.replaceAll("&","and");
		result = result.replaceAll("\\|","or");
		result = result.replaceAll("!","not");
		return result;
	}
	
	static boolean isOperator(String C) 
	{ 
		return (C.equals("=") || C.equals("<") || C.equals(">") || C.equals("-") || C.equals("+") || C.equals("*") || C.equals("/") || C.equals("^") || C.equals("&") || C.equals("|") || C.equals("!")); 
	} 

	// Function to find priority 
	// of given operator. 
	static int getPriority(String C) 
	{   if(C.equals("|") || C.equals("!"))
	        return 1;
	    else if(C.equals("&"))
	        return 2;
	    else if (C.equals("=") || C.equals("<") || C.equals(">")) 
			return 3; 
		else if (C.equals("-") || C.equals("+")) 
			return 4; 
		else if (C.equals("*") || C.equals("/")) 
			return 5; 
		else if (C.equals("^")) 
			return 6; 
		return 0; 
	} 

	// Function that converts infix 
	// expression to prefix expression. 
	static String infixToPrefix(String infix) 
	{ 
		// stack for operators. 
		Stack<String> operators = new Stack<String>(); 

		// stack for operands. 
		Stack<String> operands = new Stack<String>(); 
		int initial=-1;

		for (int i = 0; i < infix.length(); i++) 
		{ 

			// If current character is an 
			// opening bracket, then 
			// push into the operators stack. 
			//if(infix.charAt(i))
			if (infix.charAt(i) == '(') 
			{ 
				operators.push(infix.substring(i,i+1)); 
			} 

			// If current character is a 
			// closing bracket, then pop from 
			// both stacks and push result 
			// in operands stack until 
			// matching opening bracket is 
			// not found. 
			else if (infix.charAt(i) == ')') 
			{ if(initial!=-1){operands.push((infix.substring(initial,i)).replaceAll("\\s+",""));
			    initial=-1;}
			    if(operators.peek().equals("!")){
		    String op1 = operands.peek(); 
			operands.pop(); 

			String op = operators.peek(); 
			operators.pop(); 

			String tmp = "( "+op+" "+ op1+" )"; 
			operands.push(tmp); 
		}
				while (!operators.empty() && 
					!operators.peek().equals("(") ) 
					{ 

					// operand 1 
					String op1 = operands.peek(); 
					operands.pop(); 

					// operand 2 
					String op2 = operands.peek(); 
					operands.pop(); 

					// operator 
					String op = operators.peek(); 
					operators.pop(); 

					// Add operands and operator 
					// in form operator + 
					// operand1 + operand2. 
					String tmp = "( "+op+" " + op2 +" "+ op1+" )"; 
					operands.push(tmp); 
				} 

				// Pop opening bracket 
				// from stack. 
				
				operators.pop(); 
			} 

			// If current character is an 
			// operand then push it into 
			// operands stack. 
			else if (!isOperator(infix.charAt(i)+"")) 
	    	{   if(initial==-1){
	    		    initial=i;
	    		}
	    		if(i+1>=infix.length()){operands.push((infix.substring(initial,i+1)).replaceAll("\\s+",""));
			    initial=-1;}
	    		//i++;
				//operands.push(infix.charAt(i) + ""); 
			} 

			// If current character is an 
			// operator, then push it into 
			// operators stack after popping 
			// high priority operators from 
			// operators stack and pushing 
			// result in operands stack. 
			else
			{ if(initial!=-1){operands.push((infix.substring(initial,i)).replaceAll("\\s+",""));
			    initial=-1;}
				while (!operators.empty() && 
					getPriority(infix.substring(i,i+1)) <= 
						getPriority(operators.peek())) 
					{ 

					String op1 = operands.peek(); 
					operands.pop(); 

					String op2 = operands.peek(); 
					operands.pop(); 

					String op = operators.peek(); 
					operators.pop(); 

					String tmp = "( "+op+" " + op2 +" "+ op1+" )"; 
					operands.push(tmp); 
				} 
	            if(i+1<infix.length() && isOperator(infix.charAt(i+1)+"")){
	                operators.push(infix.charAt(i)+""+infix.charAt(i+1));
	                i++;
	            }
	            else{
	                operators.push(infix.charAt(i)+"");
	            }
				 
			} 
		} 

		// Pop operators from operators 
		// stack until it is empty and 
		// operation in add result of 
		// each pop operands stack. 
		if((operands.size()==1) &&!operators.empty()){
		    String op1 = operands.peek(); 
			operands.pop(); 

			String op = operators.peek(); 
			operators.pop(); 

			String tmp = "( "+op+" "+ op1+" )"; 
			operands.push(tmp); 
		}
		else{
		    while (!operators.empty()) 
		{ 
			String op1 = operands.peek(); 
			operands.pop(); 

			String op2 = operands.peek(); 
			operands.pop(); 

			String op = operators.peek(); 
			operators.pop(); 

			String tmp = "("+op+" "+ op2 +" "+ op1+")"; 
			operands.push(tmp); 
		} 
	}
		// Final prefix expression is 
		// present in operands stack. 
		return operands.peek(); 
	} 
	// Driver code 
	/*public static void main(String args[]) 
	{ 
		String s = "xa12+2*2y> =a+b"; 
		System.out.println( infixToPrefix(s.replaceAll("\\s+",""))); 
	} */
	
	public static void main(String args[])
	{
		String g="(!(X>20)) & (Y>30) & (!(Z>30))";
		System.out.println(parse(g));
	}
} 
	
	
	
	

