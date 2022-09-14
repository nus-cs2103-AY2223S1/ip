package chacha.commands;

import chacha.Storage;
import chacha.TaskList;
import chacha.Ui;
import chacha.tasks.Task;

/**
 * Command to mark task.
 */
public class MarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructor with task index to mark.
     * 
     * @param task Task index of task to mark.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }   

    /** 
     * Marks task from task list.
     * 
     * @param taskList Task list to mark task from.
     * @param ui Ui to handle printing message.
     * @param storage Storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.get(taskIndex);
        assert task != null : "task should not be null";
        task.markAsDone();
        ui.printMark(task);
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

}