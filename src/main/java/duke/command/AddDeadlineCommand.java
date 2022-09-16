package duke.command;

import java.time.LocalDate;

import duke.DukeException;
import duke.TaskList;
import duke.task.DeadlineTask;
import duke.task.Task;

/**
 * Represents a command to add a new deadline item to the list.
 */
public class AddDeadlineCommand extends Command {
    private final String description;

    public AddDeadlineCommand(String description) {
        assert description.length() > 0 : "AddDeadlineCommand description should not be empty";
        this.description = description;
    }

    /**
     * {@inheritDoc}
     */
    public String execute(TaskList tasks) throws DukeException {
        assert description.contains(" /by") : "Deadline command should have /by";
        String by = description.split(" /by ")[1];
        LocalDate date = LocalDate.parse(by);
        String info = description.split(" /by ")[0];
        Task dTask = new DeadlineTask(info, date);
        return tasks.addTask(dTask);
    }
}
