package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import task.Task;

public class LoadCommand extends Command{

    private String logFileAddress = "";
    
    public LoadCommand() {
        super();
    }

    public LoadCommand(String newAddress) {
        this.logFileAddress = newAddress;
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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        try {
            if (this.logFileAddress.equals("")) {
                return ui.load(storage.loadLog());
            } else {
                return ui.load(storage.loadLog(this.logFileAddress));
            }
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
