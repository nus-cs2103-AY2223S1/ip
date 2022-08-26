package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getDate() {
        return "";
    }

    /**
     * Returns String representation of the to-do task.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
