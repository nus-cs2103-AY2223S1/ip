/**
 * Tasks without date/time info
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo with the given description
     *
     * @param description The task description
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    protected String getTypeSymbol() {
        return "T";
    }
}
