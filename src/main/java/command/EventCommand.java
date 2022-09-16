package command;

import exception.DukeException;
import exception.InvalidDateException;
import exception.MissingArgumentException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Event;
import task.Task;

public class EventCommand extends Command{

    private String description;
    private String duration;

    public EventCommand(String description, String duration) {
        super();
        this.description = description;
        this.duration = duration;
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
            return new Event(description, duration);
        } catch (MissingArgumentException e) {
            throw new DukeException(e.getLocalizedMessage());
        } catch (InvalidDateException e) {
            throw new DukeException(e.getLocalizedMessage());
        }
    }
}
