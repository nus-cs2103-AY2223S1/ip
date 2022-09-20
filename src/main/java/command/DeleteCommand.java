package command;

import duke.TaskList;
import duke.Ui;
import task.Task;

/**
 * An abstract class that represents DeleteCommand which extends Command
 *
 * @author  Wee Xin Yang, Markus
 * @version 0.2
 * @since   2022-9-15
 */
public class DeleteCommand extends Command {
    private TaskList taskList;
    private int index;
    private Ui ui;

    /**
     * Constructor for DeleteCommand Object
     */
    public DeleteCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
        this.ui = new Ui();
    }

    /**
     * Returns a string of the executed DeleteCommand
     * @return a string after the execution of DeleteCommand
     */
    @Override
    public String execute() {
        Task task = taskList.get(index);
        taskList.remove(index);
        String taskName = task.toString();
        return ui.deleteMessage(taskName, taskList.size());
    }
}