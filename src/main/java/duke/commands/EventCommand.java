package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;

/**
 * Represents a command to create an event.
 */
public class EventCommand extends Command {
    private final String input;

    /**
     * Constructs a command to create a new event with the specified input.
     *
     * @param input The specified user input.
     */
    public EventCommand(String input) {
        this.input = input;
    }

    /**
     * {@inheritDoc}
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (input.isBlank()) {
            throw new DukeException("Hold up! Description cannot be empty!");
        }

        String[] str = input.split(" /at ", 2);
        if (str.length < 2 || str[1].trim().length() == 0) {
            throw new DukeException("Wait! When is this event??");
        }
        Event newEvent = new Event(str[0], str[1]);

        taskList.addTask(newEvent);
        storage.save(taskList);
        return ui.showAddTask(newEvent, taskList);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }
}
