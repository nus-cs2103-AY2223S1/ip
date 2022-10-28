package duke.command;

import duke.task.Event;
import duke.task.TaskList;

/**
 * Represents a event command
 */
public class EventCommand implements Command {
    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add an event. Parameters: DESCRIPTION DATETIME. Example: " + COMMAND_WORD
            + " go to library /at 2022-08-22";
    public static final String REGEX = "(?<description>.+?)\\s/at\\s(?<dateTime>.+)";

    private String description;
    private String dateTime;

    /**
     * Constructor for a {@link EventCommand}
     *
     * @param description Description for the event
     * @param dateTime    Date and time for the event
     */
    public EventCommand(String description, String dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    /**
     * Executes a command
     *
     * @param taskList
     */
    @Override
    public String execute(TaskList taskList) {
        Event event = new Event(description, dateTime);
        return taskList.add(event);
    }
}
