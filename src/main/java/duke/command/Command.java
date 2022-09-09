package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * {@code Command} is an abstract class to represent all the user commands
 */
public abstract class Command {
    public static boolean isExit = false;

    /**
     * To execute the user command
     * @param tasks the current {@code TaskList}
     * @param ui the current {@code Ui}
     * @param storage the current {@code Storage}
     * @throws DukeException if there is an error
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
