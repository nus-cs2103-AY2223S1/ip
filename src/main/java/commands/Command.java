package commands;

import exceptions.DukeException;
import managers.TaskManager;

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
     * @return Status message
     */
    String execute(TaskManager taskManager) throws DukeException;
}
