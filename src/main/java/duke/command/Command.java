package duke.command;

import duke.duke.DukeException;
import duke.util.Storage;
import duke.task.TaskList;

/** Abstract class that represents the commands the user gives to the Duke program. */
public abstract class Command {
    /**
     * Executes command and return string reponse from execution.
     *
     * @param taskList Tasklist used in Duke application.
     * @param storage Storage used in Duke application.
     * @return String response from execution of command.
     * @throws DukeException If command does not execute as successfully.
     */
    public abstract String execute(TaskList taskList, Storage storage) throws DukeException;

    /**
     * Represents if command is an Exit command.
     *
     * @return True if command is an Exit command.
     */
    public abstract boolean isExit();
}
