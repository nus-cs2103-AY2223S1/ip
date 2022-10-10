package command;

import meower.Storage;
import meower.TaskList;
import meower.Ui;
import task.Task;

public class ListArchiveCommand extends Command {

    public ListArchiveCommand() {
        super();
    }

    /** 
     * Executes the functionality of the command, in the tasklist, UI and storage that are taken in as arguments, 
     * in this case it lists all archives in the storage
     * @param tasks tasklist from Meower chatbot
     * @param ui ui from Meower chatbot
     * @param storage storage from Meower chatbot
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.listArchive(storage);
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
