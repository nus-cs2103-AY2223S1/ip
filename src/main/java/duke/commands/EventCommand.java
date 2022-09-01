package duke.commands;

import java.time.LocalDateTime;

import duke.exceptions.DukeException;
import duke.tasks.Event;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

/**
 * This class performs create an event task with specified description
 * and date time command.
 */
public class EventCommand implements Command {
    /** Event task to be added into TaskList */
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
        return Ui.formatAddTaskString(event);
    }
}
