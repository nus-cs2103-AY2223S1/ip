package duke.commands;

import duke.exceptions.DukeException;
import duke.task.TaskList;

public abstract class Command {
    protected TaskList tasks;

    public void setData(TaskList tasks) {
        this.tasks = tasks;
    }

    public abstract CommandResult execute() throws DukeException;
}
