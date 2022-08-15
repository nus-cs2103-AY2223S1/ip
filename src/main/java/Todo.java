public class Todo extends Task {
    private static final String SYMBOL = "[T]";
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return SYMBOL + super.toString();
    }
}
