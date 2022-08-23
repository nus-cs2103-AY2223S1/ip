package duke.commands;

import duke.DukeException;
import duke.Ui;
import duke.task.TaskList;

/**
 * Generic command representing a possible user input.
 */
public abstract class Command {

    /**
     * Performs the command on the actual list of tasks.
     * @param tasks List of tasks to perform command on.
     * @param ui UI class to handle any outputs.
     * @throws DukeException if command fails to run properly
     */
    public abstract void execute(TaskList tasks, Ui ui) throws DukeException;

    /**
     * Define if the command should cause the program to terminate
     * @return whether the program should terminate.
     */
    public boolean isExit() {
        return false;
    }
}
