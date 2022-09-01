package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * This class represents a command to delete a task from the task list
 */
public class DeleteCommand extends Command {

    private int taskNumber;

    /**
     * Constructs a new Delete Command
     * @param taskNumber The task to delete
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Checks if the command is an Exit Command
     * @return True if it is an Exit Command
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the Command
     * @param ui Prints the messages based on the type of Command
     * @param storage Saves the modified list of tasks
     * @param taskList List of tasks
     * @throws DukeException if invalid inputs are provided
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task deletedTask = taskList.deleteTask(taskNumber);
        storage.save(taskList);
        return ui.printDeleteTask(deletedTask, taskList.getSize());
    }
}
