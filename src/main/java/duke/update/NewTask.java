package duke.update;

import duke.exception.DukeException;
import duke.task.Task;

/**
 *
 */
public abstract class NewTask {
    public abstract Task create() throws DukeException;
}
