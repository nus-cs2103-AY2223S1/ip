package command;

import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    
    /** 
     * Checks if command will cause chatbot to end
     * @return boolean
     */
    @Override
    public boolean isEnd() {
        return false;
    }

    
    /** 
     * Executes the functionality of the command, in the tasklist, UI and storage that are taken in as arguments, 
     * in this case it lists all tasks in the tasklist
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.list(tasks);

    }

    
    /** 
     * Returns the task that will be generated from the command, returns an empty task if no task is to be generated
     * @return Task
     */
    @Override
    public Task getTask() {
        return Task.empty();
    }
}
