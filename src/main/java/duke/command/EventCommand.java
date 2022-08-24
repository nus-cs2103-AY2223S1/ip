package duke.command;

import duke.task.Event;

public class EventCommand extends TaskCommand {

    public static final String COMMAND_NAME = "event";

    public EventCommand(Event newEvent) {
        super(newEvent);
    }
}
