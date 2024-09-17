package duke.command;

import java.io.IOException;

import duke.data.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;

/** Represents an executable command */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @return String.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException;

    /**
     * Returns True if is a exit command. False otherwise.
     *
     * @return boolean.
     */
    public boolean isExit() {
        return false;
    }

}
