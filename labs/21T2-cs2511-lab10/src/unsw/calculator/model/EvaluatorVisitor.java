package unsw.calculator.model;

import unsw.calculator.model.tree.BinaryOperatorNode;
import unsw.calculator.model.tree.NumericNode;
import unsw.calculator.model.tree.TreeNode;

import java.util.Stack;

public class EvaluatorVisitor implements Visitor{

    Stack<Integer> myStack = new Stack<>();
    @Override
    public void visitBinaryOperatorNode(BinaryOperatorNode node) {
        node.acceptLeft(this);
        node.acceptRight(this);
        int val1 = myStack.pop();
        int val2 = myStack.pop();
        myStack.push(node.compute(val2, val1));
    }

    @Override
    public void visitNumericNode(NumericNode node) {
        myStack.push(node.getValue());
    }

    public int getValue() {
        return myStack.peek();
    }
}