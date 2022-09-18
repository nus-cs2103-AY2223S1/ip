package command;

import exception.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

/**
 * Represents a "bye" command from the user that will end the chatbot.
 * @extends Command
 */

public class ByeCommand extends Command{
    
    public ByeCommand() {
    }

    
    /** 
     * Checks if command will cause chatbot to end.
     * @return boolean
     */
    @Override
    public boolean isEnd() {
        return true;
    }

    
    /** 
     * Executes the functionality of the command, in the tasklist, 
     * UI and storage that are taken in as arguments, in this case saves the chatbot logs
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        try {
            storage.cleanUp();
        } catch (DukeException e) {
            throw e;
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
