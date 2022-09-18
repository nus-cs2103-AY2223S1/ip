package command;

import exception.DukeException;
import exception.TaskListOutOfBoundsException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

public class MarkCommand extends Command{

    private int pos;
    
    public MarkCommand(String pos) {
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
     * in this case marks the task that the user specified as done
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        try {
            tasks.mark(this.pos);
            ui.mark(this.pos);
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
