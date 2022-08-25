package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Command to unmark task as done.
 */
public class UnmarkCommand extends Command {
    private int num;

    /**
     * Constructor for UnmarkCommand
     *
     * @param cmd Type of command
     * @param num Index of task
     */
    public UnmarkCommand(String cmd, int num) {
        super(cmd);
        this.num = num;
    }
    public int getNum() {
        return num;
    }

    /**
     * Execute the unmark operation by changing
     * boolean isMarked of task at index num to false.
     *
     * @param ui Ui to show unmark operation messages
     * @param taskList TaskList to execute unmark command
     * @throws DukeException If invalid commands or arguments
     */
    @Override
    public void execute(Ui ui, TaskList taskList) throws DukeException {
        Task task = taskList.getTask(num);
        task.unmarkT();
        ui.showUnmarkMsg(task);
    }
}
