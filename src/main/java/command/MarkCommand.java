package main.java.command;

import main.java.exception.DukeException;
import main.java.exception.TaskListOutOfBoundsException;
import main.java.main.Storage;
import main.java.main.TaskList;
import main.java.main.Ui;
import main.java.task.Task;

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
