package bool;

import java.util.Stack;

public class BooleanEvaluator {

    public static boolean evaluate(BooleanNode expression) {
        // Return the expression evaluated
        return expression.getValue();
    }

    public static String prettyPrint(BooleanNode expression) {
        // Pretty print the expression
        return expression.getString();
    }

    public static void main(String[] args) {
        // (AND true false)
        BooleanNode node1 = new BoolAndOperation(new BooleanLeaf(true), new BooleanLeaf(false));
        System.out.println(BooleanEvaluator.evaluate(node1));
        System.out.println(BooleanEvaluator.prettyPrint(node1));

        // (OR false (NOT false))
        BooleanNode node2 = new BoolOrOperation(new BooleanLeaf(false), new BoolNotOperation(new BooleanLeaf(false)));
        System.out.println(BooleanEvaluator.evaluate(node2));
        System.out.println(BooleanEvaluator.prettyPrint(node2));

        // ((OR true (NOT (AND false (OR true false)))))
        BooleanNode node3 = new BoolOrOperation(new BooleanLeaf(true), new BoolNotOperation(new BoolAndOperation(new BooleanLeaf(false), new BoolOrOperation(new BooleanLeaf(true), new BooleanLeaf(false)))));
        System.out.println(BooleanEvaluator.evaluate(node3));
        System.out.println(BooleanEvaluator.prettyPrint(node3));
    }

}