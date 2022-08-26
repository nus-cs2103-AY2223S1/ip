package duke.commands;

import duke.task.Event;

/**
 * Creates and stores an event.
 */
public class CreateEventCommand extends CreateTaskCommand {
    /**
     * Constructor for CreateEventCommand.
     * @param event the event to be stored
     */
    public CreateEventCommand(Event event) {
        super(event);
    }
}
