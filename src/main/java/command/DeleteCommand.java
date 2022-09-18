package command;

import exception.DukeException;
import exception.TaskListOutOfBoundsException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

public class DeleteCommand extends Command{

    private int pos;
    
    public DeleteCommand(String pos) {
        super();
        this.pos = Integer.parseInt(pos);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        try { 
            ui.delete(this.pos);
            tasks.delete(this.pos);
        } catch (TaskListOutOfBoundsException e) {
            throw new DukeException(e.getLocalizedMessage());
        }
    }

    @Override
    public Task getTask() {
        return Task.empty();
    }
    
}
