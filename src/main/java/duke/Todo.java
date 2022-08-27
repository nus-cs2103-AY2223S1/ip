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
        int value;
        if(isDone) {
            value = 1;
        } else {
            value = 0;
        }
        return "T" + KEY_SEPARATOR + value + KEY_SEPARATOR + description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
