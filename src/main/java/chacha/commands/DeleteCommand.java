package chacha.commands;

import chacha.Storage;
import chacha.TaskList;
import chacha.Ui;
import chacha.tasks.Task;

/**
 * Command to delete task.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Constructor with input index to delete.
     * 
     * @param taskIndex Task to be added.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }   

    /** 
     * Deletes task from task list and saves to storage.
     * 
     * @param taskList Task list to delete task from.
     * @param ui Ui to handle printing message.
     * @param storage Storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.get(taskIndex);
        assert task != null : "task should not be null";
        taskList.remove(taskIndex); 
        int size = taskList.getSize();
        ui.printDelete(task, size);
    }

    
    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

}