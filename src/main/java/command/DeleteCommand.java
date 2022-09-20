package command;

import exception.MeowerException;
import exception.TaskListOutOfBoundsException;
import meower.Storage;
import meower.TaskList;
import meower.Ui;
import task.Task;

/**
 * Represents a "bye" command from the user that will end the chatbot.
 */
public class DeleteCommand extends Command{

    private int pos;
    
    public DeleteCommand(String pos) {
        super();
        this.pos = Integer.parseInt(pos);
    }
    
    /** 
     * Executes the functionality of the command, in the tasklist, UI and storage that are taken in as arguments
     * @param tasks tasklist from Meower chatbot
     * @param ui ui from Meower chatbot
     * @param storage storage from Meower chatbot
     * @throws MeowerException Main Meower chatbot Exception
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MeowerException{
        try {   
            tasks.delete(this.pos);
            return ui.delete(this.pos);
        } catch (TaskListOutOfBoundsException e) {
            throw new MeowerException(e.getLocalizedMessage());
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
