/**
 * This class is used to construct a Todo task.
 */
package duke;

import static duke.DukeConstants.KEY_SEPARATOR;

public class Todo extends Task {

    /**
     * Constructor for the Todo class.
     *
     * @param description Task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String formatToSave() {
        return isDone
                ? "T" + KEY_SEPARATOR + 1 + KEY_SEPARATOR + description
                : "T" + KEY_SEPARATOR + 0 + KEY_SEPARATOR + description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
