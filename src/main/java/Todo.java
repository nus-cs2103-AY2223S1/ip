public class Todo extends Task{
    /**
     * Constructor for a Todo.
     * @param description The description of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
