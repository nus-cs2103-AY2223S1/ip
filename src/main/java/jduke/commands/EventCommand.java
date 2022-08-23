package jduke.commands;

import jduke.task.Event;

public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String FORMAT = "event <description> /at <dd/mm/yyyy> <hhmm | optional>";

    private final String description;
    private final String timing;
    public EventCommand(String params) {
        String[] eventParams = params.split(" /at ", 2);
        this.description = eventParams[0];
        this.timing = eventParams[1];
    }

    @Override
    public String execute() {
        return this.tasklist.addEvent(this.description, this.timing);
    }
}
