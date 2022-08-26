package duke;

import static duke.DukeConstants.KEY_SEPARATOR;

public class Todo extends Task {

    /**
     * Takes in a description for the task
     * @param description task description
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String formatToSave() {
        return isDone
                ? "T" + KEY_SEPARATOR + 1 + KEY_SEPARATOR + description
                : "T" + KEY_SEPARATOR + 0 + KEY_SEPARATOR + description;
    }
    /**
     * Returns a String representation of the task
     * @return string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
