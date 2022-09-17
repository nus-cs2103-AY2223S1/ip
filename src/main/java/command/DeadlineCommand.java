package main.java.command;

import main.java.exception.DukeException;
import main.java.exception.InvalidDateException;
import main.java.exception.MissingArgumentException;
import main.java.main.Storage;
import main.java.main.TaskList;
import main.java.main.Ui;
import main.java.task.Deadline;
import main.java.task.Task;

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
