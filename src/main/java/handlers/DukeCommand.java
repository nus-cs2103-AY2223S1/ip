package handlers;

import exceptions.DukeException;
import models.TaskManager;

/**
 * Encapsulates an executable command
 *
 * @author Emily Ong Hui Qi
 */

@FunctionalInterface
public interface DukeCommand {
    /**
     * Receives a task manager object from the caller and execute a particular command that may or may
     * not result in a modification of the task manager object
     *
     * @param taskManager Task Manager object to manage the list of tasks
     * @param arguments The arguments received from the handler
     * @return Status message
     */
    String execute(TaskManager taskManager, String arguments) throws DukeException;
}
