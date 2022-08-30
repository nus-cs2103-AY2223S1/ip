package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * This class encapsulates a Mark Command
 */
public class MarkCommand extends Command {

    private int taskNumber;

    /**
     * Constructs a new Mark Command
     * @param taskNumber Task of mark as completed
     */
    public MarkCommand(int taskNumber) {
        super();
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
        Task taskToMark = taskList.getTask(taskNumber - 1);
        taskToMark.markAsDone();
        storage.save(taskList);
        return ui.printMarkTask(taskToMark);
    }
}
