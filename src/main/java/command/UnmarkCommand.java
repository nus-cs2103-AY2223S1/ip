package command;

import exception.DukeException;
import exception.TaskListOutOfBoundsException;
import meower.Storage;
import meower.TaskList;
import meower.Ui;
import task.Task;

public class UnmarkCommand extends Command{

    private int pos;
    
    public UnmarkCommand(String pos) {
        super();
        this.pos = Integer.parseInt(pos);
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
     * in this case marks the task specified by the user as not done
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.unmark(this.pos);
            return ui.unmark(this.pos);
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
