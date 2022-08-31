package duke.task;

import duke.Task;

/**
 * Creates a Task object that needs to be done.
 * @author Jason
 */
public class Todo extends Task {

    /**
     * Constructs a To do object.
     * @param description Description of the to do object.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Writes this to do task into the save file format.
     * @return String to be stored in save file.
     */
    @Override
    public String saveData() {
        String marked = this.isDone ? "1" : "0";
        return "T | " + marked + " | " + this.description;
    }
}
