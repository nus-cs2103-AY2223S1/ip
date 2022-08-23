package handlers;

import entities.Deadline;
import entities.Task;
import exceptions.DukeException;
import service.Service;

import java.time.format.DateTimeParseException;

public class DeadlineHandler implements IHandler{
    private String deadlineName;
    private String flag;
    private String flagOption;

    public DeadlineHandler(HandlerFactory factory) {
        this.deadlineName = factory.taskName;
        this.flag = factory.flag;
        this.flagOption = factory.flagOption;
    }

    @Override
    public void handle(Service s) throws DukeException {
        if (this.deadlineName == null) {
            throw new DukeException("Please enter a task name!");
        }
        // TODO: Refactor to enum
        if (!flag.equals("by")) {
            throw new DukeException("Incorrect option flag!\nUsage:`deadline return book /by Sunday`");
        }
        if (this.flagOption == null) {
            throw new DukeException("Please enter a deadline!");
        }
        try {
            Task deadline = new Deadline(this.deadlineName, this.flagOption);
            s.list.add(deadline);
        } catch (DateTimeParseException ex) {
            throw new DukeException("Invalid Date/Time!\nUsage: `deadline return book /at 2/12/2019 1800`");
        }
    }
}
