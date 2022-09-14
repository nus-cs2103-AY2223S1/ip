package duke.commands;

import java.time.LocalDateTime;

import duke.commons.Storage;
import duke.commons.TaskList;
import duke.commons.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Event;

/**
 * This class performs create an event task with specified description and datetime to
 * add to TaskList command.
 */
public class EventCommand implements Command {
    public static final String COMMAND_WORD = "event";
    private Event event;

    /**
     * Constructor for class EventCommand.
     *
     * @param description Description of event.
     * @param dateTime Date and time of event.
     */
    public EventCommand(String description, LocalDateTime dateTime) {
        this.event = new Event(description, false, dateTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.addTask(event);
        storage.appendToFile(event);
        return Ui.formatAddTaskMessage(event);
    }
}
