package chacha.commands;

import chacha.Storage;
import chacha.TaskList;
import chacha.Ui;
import chacha.tasks.Task;

/**
 * Command to add task.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor with input task to add.
     * 
     * @param task Task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }   
    
    /** 
     * Adds task to task list and saves to storage.
     * 
     * @param taskList Task list to add task to.
     * @param ui Ui to handle printing message.
     * @param storage Storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        assert task != null : "task should not be null";
        taskList.add(task);  
        ui.printAdd(task, taskList);
    }
    
    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

}