package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Command to mark tasks as done.
 */
public class MarkCommand extends Command {

    private int num;

    /**
     * Constructor for MarkCommand.
     *
     * @param info type of command
     * @param num index of task
     */
    public MarkCommand(String info, int num) {
        super(info);
        this.num = num;
    }

    /**
     * Executes the mark command by changing the boolean isMarked
     * for task at index num to true.
     *
     * @param ui Ui to show mark operation messages
     * @param taskList TaskList to execute mark command
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        assert(ui != null && taskList != null);
        if (num >= taskList.getSize() || num < 0) {
            return ui.showInvalidIndexMessage();
        }
        Task task = taskList.getTask(num);
        task.setMarked();
        return ui.showMarkMessage(task);
    }
}
