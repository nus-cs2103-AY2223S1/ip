package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents a command that can be executed.
 */
public interface Command {

    /**
     * Executes a task.
     *
     * @param taskList TaskList object that contains the list of tasks
     * @param ui       Ui object
     */
    public void execute(TaskList taskList, Ui ui);
}
