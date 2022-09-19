package tasks;

public class Todos extends Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Todos.
     *
     * @param description Description of todo.
     */
    public Todos(String description) {
        super(description, false);
        this.description = description;
        this.isDone = false;
    }

    /**
     * Alternative constructor for Todos.
     *
     * @param description Description of todo.
     * @param isDone Completion status of todo.
     */
    public Todos(String description, boolean isDone) {
        super(description, isDone);
        this.description = description;
        this.isDone = isDone;
    }


    /**
     * Returns string representation of todo.
     *
     * @return String representation of todo.
     */
    @Override
    public String toString() {
        String result = "[T]" + super.toString() + this.description.strip();
        return result;
    }

    /**
     * Returns string representation of the todo task to be written to file.
     *
     * @return Todo string representation to be stored in text file.
     */
    public String fileString() {
        String write = "T / " + super.fileString() + this.description.strip();
        return write;
    }

}
