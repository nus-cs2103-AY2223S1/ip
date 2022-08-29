package duke.command;

import duke.DukeException;
import duke.util.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a DeleteCommand object.
     * 
     * @param storage   Storage class to be used
     * @param ui        Ui class to be used
     * @param taskList  TaskList that specified task is in
     * @param taskIndex Index of task to be deleted in taskList
     */
    public DeleteCommand(Storage storage, Ui ui, TaskList taskList, int taskIndex) {
        super(storage, ui, taskList);
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes the specified task from the task list.
     * 
     * @throws DukeException if taskindex is invalid
     */
    @Override
    public void execute() throws DukeException {
        taskList.removeTask(taskIndex);
    }
}
