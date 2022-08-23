package duke.commands;

import duke.task.Event;

public class CreateEventCommand extends CreateTaskCommand {
    public CreateEventCommand(Event event) {
        super(event);
    }
}