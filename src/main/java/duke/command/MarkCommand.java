package duke.command;

import duke.Duke;
import duke.Storage;
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
    public String execute(Ui ui, TaskList taskList, Storage storage) throws DukeException {
        assert(ui != null && taskList != null);
        if (num >= taskList.getSize() || num < 0) {
            throw new DukeException(ui.showInvalidIndexMessage());
        }
        Task task = taskList.getTask(num);
        task.setMarked();
        storage.writeFile(taskList);
        return ui.showMarkMessage(task);
    }
}
