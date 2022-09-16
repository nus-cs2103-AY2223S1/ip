package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * {@code Command} is an abstract class to represent all the user commands
 */
public abstract class Command {
    private static boolean isExit = false;

    /**
     * To check whether the program is supposed to exit
     * @return whether to exit the program
     */
    public static boolean isExit() {
        return isExit;
    }

    /**
     * To let the program knows that it has come to an end
     */
    public static void end() {
        isExit = true;
    }

    /**
     * To execute the user command
     * @param tasks the current {@code TaskList}
     * @param ui the current {@code Ui}
     * @param storage the current {@code Storage}
     * @throws DukeException if there is an error
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
