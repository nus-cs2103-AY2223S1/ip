package duke.task;

import duke.core.DukeException;
import duke.task.Task;

/**
 * A task without any date/time attached to it e.g., visit new theme park
 */
public class ToDo extends Task {

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
