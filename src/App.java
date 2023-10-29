/*
 * Building a Tree-Based Calculator Project for Practical Data Structures
 * Dr. عبدالباسط عبدالعزيز حميده قدح
 * 
 * Group number: 2
 * Group members:
 * - سعد نور بتوا 444004616
 * - عبدالرحمن فارس منصر 444000076
 * - عبدالرحمن عمر باسيف 444004638
 * - احمد عبدالرحمن دوبي 443005399
 * - حسين هاني الصائغ 444004253
 */

import java.util.Scanner;

public class App {

    public static String cleanInput(String input) {
        // Making sure there is a space betwwen each letter except numbers
        input = input.replaceAll("[^0-9]", " $0 ");
        // Making sure it's ONLY one space
        input = input.replaceAll(" +", " ");
        return input;
    }

    public static int getPrecedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
            case "%":
                return 2;
        }
        return -1;
    }

    public static String infixToPostfix(String input) {
        Stack<String> stack = new Stack<>();
        Scanner inputScanner = new Scanner(input);
        String postfix = "";
        while (inputScanner.hasNext()) {
            String next = inputScanner.next();
            // If next is a number (operand)
            if (next.matches("[0-9]+")) {
                postfix += next + " ";
            }
            // If next is an open bracket
            else if (next.matches("\\(")) {
                stack.push(next);
            }
            // If next is a close bracket
            else if (next.matches("\\)")) {
                while (!stack.isEmpty()) {
                    String popped = stack.pop();
                    if (popped.equals("(")) {
                        break;
                    }
                    postfix += popped + " ";
                }
            }
            // If next one of the following operators: +, -, *, /
            else if (next.matches("[\\+\\-\\*\\/\\%]")) {
                while (!stack.isEmpty() && getPrecedence(stack.peek()) >= getPrecedence(next)) {
                    postfix += stack.pop() + " ";
                }
                stack.push(next);
            }
            // Else if it is not a number nor an operand
            else {
                throw new RuntimeException("Invalid input");
            }
        }
        // popping the rest of operands/operators
        while (!stack.isEmpty()) {
            postfix += stack.pop() + " ";
        }
        inputScanner.close();
        return postfix;
    }

    public static TreeNode<String> generateExpressionTree(String postfix) {
        Stack<TreeNode<String>> stack = new Stack<>();
        Scanner postfixScanner = new Scanner(postfix);
        while (postfixScanner.hasNext()) {
            String next = postfixScanner.next();
            // If next is a number (operand)
            if (next.matches("[0-9]+")) {
                stack.push(new TreeNode<>(next));
            }
            // If next one of the following operators: +, -, *, /
            else if (next.matches("[\\+\\-\\*\\/\\%]")) {
                TreeNode<String> right = stack.pop();
                TreeNode<String> left = stack.pop();
                stack.push(new TreeNode<>(next, left, right));
            }
        }
        postfixScanner.close();
        return stack.pop();
    }

    public static double evaluateExpressionTree(TreeNode<String> root) {
        if (root == null) {
            return 0;
        }
        if (root.getLeft() == null && root.getRight() == null) {
            return Double.parseDouble(root.getData());
        }
        double left = evaluateExpressionTree(root.getLeft());
        double right = evaluateExpressionTree(root.getRight());
        switch (root.getData()) {
            case "+":
                return left + right;
            case "-":
                return left - right;
            case "*":
                return left * right;
            case "/":
                if (right == 0) {
                    throw new RuntimeException("Cannot divide by zero");
                }
                return left / right;
            case "%":
                if (right == 0) {
                    throw new RuntimeException("Cannot divide by zero");
                }
                return left % right;
        }
        return 0;
    }

    public static void main(String[] args) throws Exception {
        Scanner systemScanner = new Scanner(System.in);
        String input;

        System.out.println("Welcome to the Expression Tree Generator");

        while (true) {
            System.out.println();
            System.out.print("Enter an equation or \"exit\" to exit: ");
            input = systemScanner.nextLine();

            if (input.equals("exit")) {
                System.out.println("exiting program ...");
                break;
            }

            System.out.println("\n1. Making sure there is one space between each operand and operator ...");
            input = cleanInput(input);
            System.out.println("Done: " + input);

            System.out.println("\n2. Converting infix to postfix ...");
            try {
                input = infixToPostfix(input);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                continue;
            }
            System.out.println("Done: " + input);

            System.out.println("\n3. Generating expression tree ...");
            TreeNode<String> root = generateExpressionTree(input);
            System.out.println("Done");
            root.printTree();
            System.out.println("Tree size: " + root.size());

            System.out.println("\n4. Evaluating expression tree ...");
            double result;
            try {
                result = evaluateExpressionTree(root);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                continue;
            }
            System.out.println("Done: " + result);
        }
        System.out.println("\nThank you for using the Expression Tree Generator, program closed.");
        systemScanner.close();
    }
}
