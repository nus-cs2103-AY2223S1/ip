package main.java.command;

import main.java.exception.DukeException;
import main.java.exception.MissingArgumentException;
import main.java.main.Storage;
import main.java.main.TaskList;
import main.java.main.Ui;
import main.java.task.Task;
import main.java.task.ToDo;

public class TodoCommand extends Command{

    private String description;
    
    public TodoCommand(String description) {
        super();
        this.description = description;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        try {
            Task newEvent = this.getTask();
            tasks.add(newEvent);
            ui.add(newEvent);
        } catch (DukeException e) {
            throw e;
        }
    }

    @Override
    public Task getTask() throws DukeException{
        try {
            return new ToDo(description);
        } catch (MissingArgumentException e) {
            throw new DukeException(e.getLocalizedMessage());
        }
    }

}
