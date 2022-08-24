package duke.commands;

import duke.exceptions.DukeException;
import duke.managers.TaskManager;
import duke.managers.UiManager;

/**
 * Encapsulates an executable command.
 *
 * @author Emily Ong Hui Qi
 */

@FunctionalInterface
public interface Command {
    /**
     * Receives a task manager object from the caller and executes a specified command that may or may
     * not result in a modification of the task manager object.
     *
     * @param taskManager Task manager object in the current lifecycle to manage the list of tasks
     * @param uiManager The UI manager in the current lifecycle in charge of display operations and user interactions
     * @throws DukeException If an error occurs during the execution of a command
     */
    void execute(TaskManager taskManager, UiManager uiManager) throws DukeException;
}
