package duke.commands;

import duke.DukeException;
import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Adds a new event to the task list.
 */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a new Event.\n"
            + "\tEx.: " + COMMAND_WORD + " <description> /at <date/time>";

    private final String description;
    private final String at;

    /**
     * Constructor for EventCommand.
     *
     * @param description description of the new event
     * @param at time frame of the event (date, time, etc.)
     */
    public EventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, StorageFile storage) throws DukeException {
        taskList.addEvent(description, at);
        storage.save(taskList);
    }
}
