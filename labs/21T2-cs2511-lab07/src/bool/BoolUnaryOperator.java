package bool;

public abstract class BoolUnaryOperator extends BooleanNode{
    private BooleanNode firstValue;

    public BoolUnaryOperator(BooleanNode firstValue) {
        this.firstValue = firstValue;
    }

    @Override
    public boolean getValue() {
        return applyOperation(firstValue.getValue());
    }

    public abstract boolean applyOperation(boolean firstValue);

    public abstract String getString();

    public BooleanNode getFirstValue() { return firstValue; }
}
