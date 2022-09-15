package duke.command;

import duke.duke.DukeException;
import duke.util.Storage;
import duke.task.TaskList;

/** Abstract class that represents the commands the user gives to the Duke program */
public abstract class Command {
    public abstract String execute(TaskList taskList, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
