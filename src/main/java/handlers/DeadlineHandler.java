package handlers;

import entities.Deadline;
import entities.Task;
import exceptions.DukeException;

import java.util.List;

import static utils.Utils.addToList;

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
    public void handle(List<Task> list) throws DukeException {
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
        Task deadline = new Deadline(this.deadlineName, this.flagOption);
        addToList(list, deadline);
    }
}
