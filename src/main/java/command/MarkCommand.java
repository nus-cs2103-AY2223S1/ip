package command;

import duke.TaskList;
import duke.Ui;
import task.Task;

/**
 * An abstract class that represents MarkCommand which extends Command
 *
 * @author  Wee Xin Yang, Markus
 * @version 0.2
 * @since   2022-9-15
 */
public class MarkCommand extends Command {
    private TaskList taskList;
    private int index;
    private Ui ui;

    /**
     * Constructor for MarkCommand Object
     */
    public MarkCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
        this.ui = new Ui();
    }

    /**
     * Returns a string of the executed MarkCommand
     * @return a string after the execution of MarkCommand
     */
    @Override
    public String execute() {
        Task task = taskList.get(index);
        task.mark();
        String taskName = task.toString();
        taskList.set(index, task);
        return ui.markMessage(taskName);
    }
}
