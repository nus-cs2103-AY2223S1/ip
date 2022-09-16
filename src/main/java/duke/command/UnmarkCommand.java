package duke.command;

import duke.task.TaskList;
import duke.Ui;

/**
 * Class which inherits the Command class for an UnmarkCommand
 *
 * @author kaij77
 * @version 0.1
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Public constructor for an UnmarkCommand.
     *
     * @param index The index of the Task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the UnmarkCommand by unmarking the specified Task.
     *
     * @param taskList A list of tasks
     * @param ui A ui responsible for printing output to the user
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.printUnmarkTask(taskList.unmarkTask(index));
    }
}