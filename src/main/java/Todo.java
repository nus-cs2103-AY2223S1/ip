public class Todo extends Task {
    /**
     * Child class of Task without any date/time attached to it
     */
    private static final String SYMBOL = "[T]";
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return SYMBOL + super.toString();
    }
}
