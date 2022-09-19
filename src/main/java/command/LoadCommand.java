package command;

import exception.MeowerException;
import meower.Storage;
import meower.TaskList;
import meower.Ui;
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
     * Executes the functionality of the command, in the tasklist, UI and storage that are taken in as arguments, 
     * in this case it loads the tasks from a chatbot log file into the current chatbots tasklist
     * @param tasks tasklist from Meower chatbot
     * @param ui ui from Meower chatbot
     * @param storage storage from Meower chatbot
     * @throws MeowerException Main Meower chatbot Exception
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MeowerException{
        try {
            if (this.logFileAddress.equals("")) {
                return ui.load(storage.loadLog());
            } else {
                return ui.load(storage.loadLog(this.logFileAddress));
            }
        } catch (MeowerException e) {
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
