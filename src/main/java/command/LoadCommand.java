package command;

import exception.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

public class LoadCommand extends Command{
    
    public LoadCommand() {
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
     * in this case it loads the tasks from a chatbot log file into the current chatbots tasklist
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        try {
            storage.loadLog();
        } catch (DukeException e) {
            throw e;
        }
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
