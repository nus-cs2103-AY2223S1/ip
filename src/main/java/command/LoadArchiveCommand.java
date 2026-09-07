package command;

import exception.MeowerException;

import task.Task;
import meower.Storage;
import meower.TaskList;
import meower.Ui;

public class LoadArchiveCommand extends Command {

    private String archiveFileAddress;

    public LoadArchiveCommand(String newAddress) {
        super();
        this.archiveFileAddress = newAddress;
    }
 
    /** 
     * Executes the functionality of the command, in the tasklist, 
     * UI and storage that are taken in as arguments, in this case loads the tasks from an archive file into the tasklist
     * @param tasks tasklist from Meower chatbot
     * @param ui ui from Meower chatbot
     * @param storage storage from Meower chatbot
     * @throws MeowerException Main Meower chatbot Exception
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MeowerException {
        int numOfTasks = storage.loadArchive(this.archiveFileAddress);
        return ui.loadArchive(numOfTasks);
    }
    
    /** 
     * Returns the task that will be generated from the command, returns an empty task if no task is to be generated
     * @return Task
     */
    public Task getTask() {
        return Task.empty();
    }
}

