package bool;

public class BoolAndOperation extends BoolBinaryOperator {

    public BoolAndOperation(BooleanNode firstValue, BooleanNode secondValue) { super(firstValue, secondValue); }

    @Override
    public boolean applyOperation(boolean firstValue, boolean secondValue) { return firstValue && secondValue; }

    @Override
    public String getString() { return "(AND " + getFirstValue().getString() + " " + getSecondValue().getString() + ")"; }
}


