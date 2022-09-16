package duke.command;

import duke.task.TaskList;
import duke.Ui;

/**
 * Class which inherits the Command class for a MarkCommand
 *
 * @author kaij77
 * @version 0.1
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Public constructor for a MarkCommand
     *
     * @param index The index of the task to be marked
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the MarkCommand by marking the specified Task.
     *
     * @param taskList A list of tasks
     * @param ui A ui responsible for printing output to the user
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.printMarkTask(taskList.markTask(index));
    }
}