package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Command to delete a task with given index.
 */
public class DeleteCommand extends Command {
    private int num;

    /**
     * Constructor for DeleteCommand.
     *
     * @param cmd Type of comand
     * @param num Index to delete
     */
    public DeleteCommand(String cmd, int num) {
        super(cmd);
        this.num = num;
    }
    public int getNum() {
        return num;
    }

    /**
     * Execute the delete command and remove the task at index.
     *
     * @param ui Ui to show Delete operation messages
     * @param taskList TaskList to execute delete command
     * @throws DukeException If invalid commands or arguments
     */
    @Override
    public void execute(Ui ui, TaskList taskList) throws DukeException {
        Task task = taskList.getTask(this.num);
        taskList.remove(this.num);
        int size = taskList.getSize();
        ui.showDeleteMsg(task, size);
    }
}
