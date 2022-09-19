package duke.task;

/** A class representing a task to be done, with no particular deadline. */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String convertToFile() {
        return "todo," + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
