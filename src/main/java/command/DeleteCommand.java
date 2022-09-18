package command;

import exception.DukeException;
import exception.TaskListOutOfBoundsException;
import main.Storage;
import main.TaskList;
import main.Ui;
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
     * Checks if command will cause chatbot to end.
     * @return boolean
     */
    @Override
    public boolean isEnd() {
        return false;
    }

    
    /** 
     * Executes the functionality of the command, in the tasklist, UI and storage that are taken in as arguments
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        try { 
            ui.delete(this.pos);
            tasks.delete(this.pos);
        } catch (TaskListOutOfBoundsException e) {
            throw new DukeException(e.getLocalizedMessage());
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
