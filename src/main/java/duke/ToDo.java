package duke;

import duke.Task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
        type = 'T';
    }

    public ToDo(String description, boolean isDone) {
        this(description);
        this.isDone = isDone;
    }
}
