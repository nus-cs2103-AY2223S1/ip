package duke.task;

import duke.util.DukeException;

/**
 * Task to be done without deadline.
 * @author Jicson Toh
 */
public class TodoTask extends Task {
    public TodoTask(String action, boolean isDone) throws DukeException {
        super(action, isDone, "");
    }

    public TodoTask(String action) throws DukeException {
        this(action, false);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
