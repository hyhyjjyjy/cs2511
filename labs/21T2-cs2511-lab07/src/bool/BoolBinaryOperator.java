package bool;

public abstract class BoolBinaryOperator extends BooleanNode{

    private BooleanNode firstValue, secondValue;

    public BoolBinaryOperator(BooleanNode firstValue, BooleanNode secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public BooleanNode getFirstValue() { return firstValue; }

    public BooleanNode getSecondValue() { return secondValue; }

    public abstract boolean applyOperation(boolean firstValue, boolean secondValue);

    public abstract String getString();

    @Override
    public boolean getValue() { return applyOperation(firstValue.getValue(), secondValue.getValue()); }

}
