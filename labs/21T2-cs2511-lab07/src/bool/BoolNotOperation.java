package bool;

public class BoolNotOperation extends BoolUnaryOperator {

    public BoolNotOperation(BooleanNode firstValue) {
        super(firstValue);
    }

    @Override
    public boolean applyOperation(boolean firstValue) {
        return !firstValue;
    }

    @Override
    public String getString() {
        return "(NOT " + getFirstValue().getString() + ")";
    }

}
