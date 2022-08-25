package seedu.duke;
public class Todo extends Task {

    /**
     * A constructor that creates an instance of Todo.
     *
     * It takes in the description of the task.
     *
     * @param description The description of the task.
     * @throws DukeException
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStore() {
        return "T" + super.toStore();
    }
}
