package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Command to mark tasks as done.
 */
public class MarkCommand extends Command {
    private int num;

    /**
     * Constructor for MarkCommand.
     *
     * @param cmd type of command
     * @param num index of task
     */
    public MarkCommand(String cmd, int num) {
        super(cmd);
        this.num = num;
    }
    public int getNum() {
        return num;
    }

    /**
     * Execute the mark command by changing the boolean isMarked
     * for task at index num to true.
     *
     * @param ui Ui to show mark operation messages
     * @param taskList TaskList to execute mark command
     * @throws DukeException If invalid commands or arguments
     */
    @Override
    public void execute(Ui ui, TaskList taskList) throws DukeException {
        Task task = taskList.getTask(num);
        task.markT();
        ui.showMarkMsg(task);
    }
}
