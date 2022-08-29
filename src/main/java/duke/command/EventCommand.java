package duke.command;

import duke.task.Event;
import duke.task.TaskList;

public class EventCommand implements Command {
    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add an event. Parameters: DESCRIPTION DATETIME. Example: " + COMMAND_WORD
            + " go to library /at 2022-08-22";

    private String description;
    private String dateTime;

    public EventCommand(String description, String dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    @Override
    public String execute(TaskList taskList) {
        Event event = new Event(description, dateTime);
        return taskList.add(event);
    }
}
