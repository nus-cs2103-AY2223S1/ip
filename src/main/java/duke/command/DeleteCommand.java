package duke.command;

import duke.task.TaskList;
import duke.Ui;

/**
 * Class which inherits the Command class for a DeleteCommand
 *
 * @author kaij77
 * @version 0.1
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Public constructor for a DeleteCommand.
     *
     * @param index The index of the task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the DeleteCommand by deleting the specified Task from the current TaskList.
     *
     * @param taskList A list of tasks
     * @param ui A ui responsible for printing output to the user
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.printDeleteTask(taskList.deleteTask(index));
        ui.printSizeOfList(taskList.size());
    }
}
