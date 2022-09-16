package command;

import exception.DukeException;
import exception.InvalidDateException;
import exception.MissingArgumentException;
import main.Storage;
import main.TaskList;
import main.Ui;
import task.Deadline;
import task.Task;

public class DeadlineCommand extends Command {

    private String description;
    private String date;

    public DeadlineCommand(String description, String date) {
        super();
        this.description = description;
        this.date = date;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        try {
            Task newDeadline = this.getTask();
            tasks.add(newDeadline);
            ui.add(newDeadline);
        } catch (DukeException e) {
            throw e;
        }
    }

    @Override
    public Task getTask() throws DukeException{
        try {
            return new Deadline(description, date);
        } catch (MissingArgumentException e) {
            throw new DukeException(e.getLocalizedMessage());
        } catch (InvalidDateException e) {
            throw new DukeException(e.getLocalizedMessage());
        }
    }
    
}
