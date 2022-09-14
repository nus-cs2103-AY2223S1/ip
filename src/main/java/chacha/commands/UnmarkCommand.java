package chacha.commands;

import chacha.Storage;
import chacha.TaskList;
import chacha.Ui;
import chacha.tasks.Task;

/**
 * Command to unmark task.
 */
public class UnmarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructor with task index to unmark.
     * 
     * @param task Task index of task to unmark.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }   

    /** 
     * Unmarks task from task list.
     * 
     * @param taskList Task list to unmark task from.
     * @param ui Ui to handle printing message.
     * @param storage Storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
                Task task = taskList.get(taskIndex);
                assert task != null : "task should not be null";
                task.unmarkAsDone();
                ui.printUnmark(task);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

}