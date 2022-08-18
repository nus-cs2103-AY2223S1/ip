public class ToDo extends Task{
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a ToDos object
     */
    public ToDo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
