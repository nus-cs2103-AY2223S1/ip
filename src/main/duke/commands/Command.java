package commands;

import exceptions.DukeException;
import managers.TaskManager;
import managers.UiManager;

/**
 * Encapsulates an executable command
 *
 * @author Emily Ong Hui Qi
 */

@FunctionalInterface
public interface Command {
    /**
     * Receives a task manager object from the caller and execute a particular command that may or may
     * not result in a modification of the task manager object
     *
     * @param taskManager Task Manager object to manage the list of tasks
     * @param uiManager The UI Manager in charge of display operations and user interactions
     */
    void execute(TaskManager taskManager, UiManager uiManager) throws DukeException;
}
