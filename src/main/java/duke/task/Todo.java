package duke.task;

import duke.DukeCommand;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public DukeCommand getTaskType() {
        return DukeCommand.TODO;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
