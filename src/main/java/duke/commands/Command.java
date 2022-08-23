package duke.commands;

import duke.DukeException;
import duke.Ui;
import duke.task.TaskList;

/**
 * Generic command representing a possible user input.
 */
public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
