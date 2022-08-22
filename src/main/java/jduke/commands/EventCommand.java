package jduke.commands;

import jduke.task.Event;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String FORMAT = "event <description> /at <dd/mm/yyyy> <hhmm | optional>";

    private final Event event;
    public EventCommand(String params) {
        String[] eventParams = params.split(" /at ", 2);
        this.event = new Event(eventParams[0], eventParams[1]);
    }

    @Override
    public String execute() {
        this.tasklist.addEvent(event);
        return String.format("|  added task:%n|    %s%n", this.event);
    }
}
