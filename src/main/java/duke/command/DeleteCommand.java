package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Command to delete a task with given index.
 */
public class DeleteCommand extends Command {

    private int num;

    /**
     * Constructor for DeleteCommand.
     *
     * @param info Type of command
     * @param num Index to delete
     */
    public DeleteCommand(String info, int num) {
        super(info);
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
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        Task task = taskList.getTask(this.num);
        taskList.removeTask(this.num);
        int size = taskList.getSize();
        return ui.showDeleteMessage(task, size);
    }
}
