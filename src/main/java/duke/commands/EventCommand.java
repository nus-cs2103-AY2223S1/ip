package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.Event;
import duke.tools.Storage;
import duke.tools.TaskList;
import duke.tools.Ui;

import java.time.LocalDateTime;

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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.addTask(this.event);
            storage.appendToFile(this.event);
            ui.addTask(this.event);
            ui.printListSize(taskList);
        } catch (DukeException e) {
            ui.printException(e);
        }
    }
}
