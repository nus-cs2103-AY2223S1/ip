package duke.command;

import duke.task.TaskList;

/**
 * Represents a command that can be executed.
 *
 * Inspired by AddressBook-Level2
 */
public interface Command {

    /**
     * Executes a task.
     *
     * @param taskList TaskList object that contains the list of tasks
     */
    public String execute(TaskList taskList);
}
