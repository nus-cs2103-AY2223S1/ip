package command;

import exception.MeowerException;
import meower.Storage;
import meower.TaskList;
import meower.Ui;
import task.Task;

public class ByeCommand extends Command {

    private String logFileAddress = "";
    
    public ByeCommand() {
        super();
    }

    public ByeCommand(String newAddress) {
        super();
        this.logFileAddress = newAddress;
    }
 
    /** 
     * Executes the functionality of the command, in the tasklist, 
     * UI and storage that are taken in as arguments, in this case saves the chatbot logs
     * @param tasks tasklist from Meower chatbot
     * @param ui ui from Meower chatbot
     * @param storage storage from Meower chatbot
     * @throws MeowerException Main Meower chatbot Exception
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MeowerException {
        if (this.logFileAddress.equals("")) {
            int numOfTasks = storage.saveToFile(true);
            return ui.bye(numOfTasks);
        } else {
            int numOfTasks = storage.saveToFile(this.logFileAddress);
            return ui.bye(numOfTasks);
        }
    }
    
    /** 
     * Returns the task that will be generated from the command, returns an empty task if no task is to be generated
     * @return Task
     */
    public Task getTask() {
        return Task.empty();
    }

}
