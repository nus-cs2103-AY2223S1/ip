package duke.task;

public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of a todo task.
     *
     * @return the string of todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
