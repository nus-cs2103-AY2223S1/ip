package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.task.EventTask;
import duke.task.Task;

/**
 * Represents a command to add a new event item to the list.
 */
public class AddEventCommand extends Command {
    private final String description;

    public AddEventCommand(String description) {
        assert description.length() > 0 : "AddEventCommand description should not be empty";
        this.description = description;
    }

    /**
     * {@inheritDoc}
     */
    public String execute(TaskList tasks) throws DukeException {
        assert description.contains(" /at") : "Event command should have /at";
        String at = description.split(" /at ")[1];
        String about = description.split(" /at ")[0];
        Task eTask = new EventTask(about, at);
        return tasks.addTask(eTask);
    }
}
