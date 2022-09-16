package command;

import exception.DukeException;
import exception.MissingArgumentException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;
import task.ToDo;

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
