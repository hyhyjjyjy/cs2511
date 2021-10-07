package bool;

public class BooleanLeaf extends BooleanNode{
    private boolean value;

    public BooleanLeaf(boolean value) {
        this.value = value;
    }

    @Override
    public boolean getValue() {
        return value;
    }

    @Override
    public String getString() {
        if (value) return "true";
        else return "false";
    }
}
