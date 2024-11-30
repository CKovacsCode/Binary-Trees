package osu.cse2123;
/**
@author : Connor Kovacs
@version 11252022
 */
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class ExpressionBuilder {

	/**
	 * Returns a tree based on a postfix expression string
	 * @param expr a properly formatted postfix expression string
	 * @precond expr will be properly formatted and have a space between each element
	 * @return a binary tree with the expression given in expr
	 */

	//stack calc recursive
	public static TreeNode<String> buildTreeFromString(String expr) {
		//break up our new array of strings with our split method
		String[] newArray = expr.split("\\s+");
		//new stack containing expression
		Stack<TreeNode<String>> newStack = new Stack<TreeNode<String>>();
		TreeNode<String>idx;
		// find the elements in our new array
		for(int i = 0; i < newArray.length; i++){
			// if element in new array is operator this is where we execute
			if(newArray[i].equals("+") || newArray[i].equals("-")
					|| newArray[i].equals("*") || newArray[i].equals("/")
					|| newArray[i].equals("%")) {
				if(newStack.size() < 2) {

					//we know that if our stack does not have at least 2 elements the user
					//entered the expression in wrong so we return null and say goodbye
					idx = null;
				}
				else {
					//this is where we have to create a new tree with the operator in the array
					TreeNode<String> expTreeNode = new TreeNode<String>(newArray[i]);
					// we take our first element off our newstack and pop it and then set it to our right child
					TreeNode<String> element1 = newStack.pop();
					expTreeNode.setRight(element1);
					// we take our second element off our newstack and pop it and then set it to our left child
					TreeNode<String> element2 = newStack.pop();
					expTreeNode.setLeft(element2);
					// we take the new treenode and add it to our new stack 
					newStack.push(expTreeNode);
				}
			}
			// else if element is not a number
			else{
				// make new treenode for that operand
				TreeNode<String> newTreeNode = new TreeNode<String>(newArray[i]);

				// push that new treenode to our stack
				newStack.push(newTreeNode);
			}
		}
		if(newStack.size() > 1) {
			//if the stack has more than one 1 treenode we have to set our variable idx to null
			idx = null;
		}
		else {
			// set variable to pop our new stack and return expression for user
			idx = newStack.pop();
		}
		return idx;
	} 

	/**
	 * Returns a string that is the inorder traversal of the expression tree,
	 * including parentheses
	 * @param expr a binary tree that represents a expression tree
	 * @precond expr is a properly formatted expression tree
	 * @return a String representing the inorder visit of expr
	 */
	public static String toInfixString(TreeNode<String> expr) {
		// string result to set
		String result = "";
		// if our left is not null we have to set result to our infix method
		if(expr.getLeft() != null){
			result = result + "(" + toInfixString(expr.getLeft()) + " ";
		}
		//set result to our data and append to string
		result = result + expr.getData().toString();

		// if the right is not null we do the same
		if(expr.getRight() != null){
			result = result + " " + toInfixString(expr.getRight()) + ")";
		}
		// return the infix format 
		return result;
	} 

	/**
	 * Evaluates the binary expression tree expr
	 * @param expr a binary tree that represents an expression tree
	 * @precond expr is a properly formatted expression tree
	 * @return the evaluation of the expression
	 */
	public static int evaluate(TreeNode<String> expr) {
		int result = 0;//set result
		//if our treenode expr data is a operator set to right sign
		if((expr.getData().equals("+")) || (expr.getData().equals("-"))
				|| (expr.getData().equals("*")) || (expr.getData().equals("/"))
				|| (expr.getData().equals("%"))) {
			if(expr.getData().equals("+")){
				// set result to add the left and right 
				result = evaluate(expr.getLeft()) + evaluate(expr.getRight());
			}
			else if(expr.getData().equals("-")) {
				// set result to subtract the left and right 
				result = evaluate(expr.getLeft()) - evaluate(expr.getRight());
			}
			else if(expr.getData().equals("*")) {
				// set result to multiply the left and right 
				result = evaluate(expr.getLeft()) * evaluate(expr.getRight());
			}
			else if(expr.getData().equals("/")) {
				// set result to divide the left and right 
				result = evaluate(expr.getLeft()) / evaluate(expr.getRight());
			}
			else {
				// set result to find the remainder of the left and right 
				result = evaluate(expr.getLeft()) % evaluate(expr.getRight());
			}
		}

		else
		{
			// we need to convert our result from a string to int 
			result = Integer.parseInt(expr.getData());
		}
		return result;//return result
	} 



	public static void main(String[] args) {
		int result = 0;
		Scanner scan = new Scanner(System.in);//scanner in 
		System.out.print("Enter an expression (blank to quit): ");//prompt
		String exp = scan.nextLine();//scan the users input on the next line
		TreeNode<String> new1 = buildTreeFromString(exp);//Make our new Queue of strings 
		if(new1 != null) {//If input user entered is not empty execute the loop
			buildTreeFromString(exp);//parse the string through our method
			String treeExpr = ExpressionBuilder.toInfixString(ExpressionBuilder.buildTreeFromString(exp));//build our expression

			result = ExpressionBuilder.evaluate(ExpressionBuilder.buildTreeFromString(exp));//output expression in right format
			System.out.println(treeExpr + " = " + result);//print output


		}

		if(new1 == null) {//once empty print out goodbye
			System.out.print("Goodbye!");
		}
	}
}
