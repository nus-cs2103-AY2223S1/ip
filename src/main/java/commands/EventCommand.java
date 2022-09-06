package commands;

import java.time.LocalDate;

import data.Event;

/**
 * Command to create an Event.
 */
public class EventCommand extends NewTaskCommand {
    /**
     * Creates an EventCommand
     *
     * @param title  Title of event to be created.
     * @param dateAt Date of event to be created.
     */
    public EventCommand(String title, LocalDate dateAt) {
        super(new Event(title, false, dateAt));
    }
}
