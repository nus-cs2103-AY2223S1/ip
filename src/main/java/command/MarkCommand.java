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

    @Override
    public boolean isEnd() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        try {
            tasks.mark(this.pos);
            ui.mark(this.pos);
        } catch (TaskListOutOfBoundsException e) {
            throw new DukeException(e.getLocalizedMessage());
    }
    }

    @Override
    public Task getTask() {
        return Task.empty();
    }
}
