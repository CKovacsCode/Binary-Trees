package osu.cse2123;

import java.util.Stack;

/**
 * A collection of Binary Tree methods
 *
 * @author Connor Kovacs
 * @version 11262023
 */


public class BinaryTreeMethods {

	/**
	 * Builds a tree from a StringBuilder string
	 * @param str StringBuilder containing a correct tree definition
	 * @precond str is a correct string definition of a tree with no whitespace
	 * @return a binary tree derived from str
	 */
	public static TreeNode<String> build(StringBuilder str) {
		assert str.charAt(0) == '('; // fail if the first character is not a paren
		str.delete(0,1); // remove the '('
		TreeNode<String> root = null; // empty tree is default
		if (str.charAt(0)!=')') {
			// we don't have an empty tree
			int end = 0;
			// find the value of the root node
			while (end < str.length() && str.charAt(end) != '(') {
				end++;
			}
			String val = str.substring(0,end);
			str.delete(0,end); //remove root data value
			// build the left tree
			TreeNode<String> left = build(str);
			// after building the left tree, fail if the next character is not a paren
			assert str.charAt(0) == '(';
			// build the right tree
			TreeNode<String> right = build(str);
			// fail if the next character is not a close paren 
			// this indicates the end of the node
			assert str.charAt(0) == ')';
			root = new TreeNode<>(val);
			root.setLeft(left);
			root.setRight(right);
		}
		str.delete(0,1); // remove closing paren ending the node definition
		return root;
	}



	/**
	 * Returns the size of the binary tree
	 * 
	 * @param root the root node of the binary tree
	 * @return the count of all of the nodes in the tree
	 */
	public static int size(TreeNode<String> root) {
		int count=0;
		if (root != null) {
			count = 1 + size(root.getLeft()) + size(root.getRight());
		}
		return count;
	}

	/**
	 * Checks to see if a value is in a binary tree
	 * @param root root of the tree to check
	 * @param value value to search for
	 * @return true if value is in the tree, false otherwise
	 */
	public static boolean contains(TreeNode<String> root, String value) {
		boolean result = true;//set our result
		if (root != null) {//if root is null
			result = true;//set result to true
		}else {
			result = false;
		}
		return result;//return result
	}

	/**
	 * inOrderTraversal
	 *   in-order traversal logic is:
	 *   	1. Process the left child first
	 *   	2. Then process the current node
	 *   	3. Then process the right child
	 *   So if the tree looks like this:
	 *        5
	 *       / \
	 *      2   9
	 *   The method would return the String "2 5 9"
	 * @param root the root node of the tree you want to traverse
	 * @return a String containing the inOrderTraversal representation of this tree
	 *   
	 */
	public static String inOrderTraversal(TreeNode<String> root) {

		String result = " "; // state result
		if(root == null) {//if root is null return empty string
			return "";
		}

		if(root.getLeft() != null) {//check left

			result = inOrderTraversal(root.getLeft());

		}
		if(root.getRight() != null) {//check right

			result = " " + inOrderTraversal(root.getRight());


		}
		if(root != null) {//traverse down the tree
			result = inOrderTraversal(root.getLeft()) + root.getData() + inOrderTraversal(root.getRight());
		}
		return result;//return our result
	}

	/**
	 * preOrderTraversal
	 *   pre-order traversal logic is:
	 *   	1. Process the current node first
	 *   	2. Then process the left child
	 *   	3. Then process the right child
	 *   So if the tree looks like this:
	 *        5
	 *       / \
	 *      2   9
	 *   The method would return the String "5 2 9"
	 * @param root the root node of the tree you want to traverse
	 * @return a String containing the preOrderTraversal representation of this tree
	 */
	public static String preOrderTraversal(TreeNode<String> root) {

		String result = "";//result
		if (root != null) {//if root is null 
			result = root.getData() + "" + preOrderTraversal(root.getRight()) + " " + preOrderTraversal(root.getLeft());
		}
		return result;//return our result
	}

	/**
	 * postOrderTraversal
	 *   post-order traversal logic is:
	 *   	1. Process the left child first
	 *   	2. Then process the right child
	 *   	3. Then process the current node
	 *   So if the tree looks like this:
	 *        5
	 *       / \
	 *      2   9
	 *   The method would return the String "2 9 5"
	 * @param root the root node of the tree you want to traverse
	 * @return a String containing the postOrderTraversal representation of this tree
	 */
	public static String postOrderTraversal(TreeNode<String> root) {

		String result = "";//string result
		if (root != null) {//if root is null
			result = preOrderTraversal(root.getLeft()) + ""  + preOrderTraversal(root.getRight()) + "" + root.getData();
		}
		return result;//return our result
	}

	public static void main(String[] args) {
		TreeNode<String> root = build(new StringBuilder("()"));
		System.out.println(root);
		root = build(new StringBuilder("(a()())"));
		System.out.println(root);
		root = build(new StringBuilder("(a(b()())(c()()))"));
		System.out.println(root);
		root = build(new StringBuilder("(a(b(d()())())(c()(e()())))"));
		System.out.println(root);
		System.out.println(size(root));
		System.out.println(inOrderTraversal(root));
		System.out.println(preOrderTraversal(root));
		System.out.println(postOrderTraversal(root));

	}


}
