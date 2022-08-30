package duke;

public class Todo extends Task{
    protected String by;

    public Todo (String description) {
        super(description);
    }

    public Todo (String description, boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    /**
     * Encodes the task to string for storage.
     * @return the encoded string
     */
    @Override
    public String encodeToString() {
        return "T|" + this.getStatusIcon() + "|" + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}