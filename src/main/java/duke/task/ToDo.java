package duke.task;

import duke.common.DukeException;

public class ToDo extends Task {
    public ToDo(String description, boolean isDone) throws DukeException {
        super("todo", description, isDone);
    }
}
