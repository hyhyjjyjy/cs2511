package bool;

public class BoolOrOperation extends BoolBinaryOperator {

        public BoolOrOperation(BooleanNode a, BooleanNode b) {
            super(a, b);
        }

        @Override
        public boolean applyOperation(boolean a, boolean b) {
            return a || b;
        }

        @Override
        public String getString() {
            return "(OR " + getFirstValue().getString() + " " + getSecondValue().getString() + ")";
        }

}
