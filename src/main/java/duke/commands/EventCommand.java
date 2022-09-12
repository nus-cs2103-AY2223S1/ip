package duke.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidDateException;
import duke.exceptions.WrongEventFormatException;
import duke.task.Event;
import duke.undo.TaskUndo;

/**
 * Represents an event command in the Duke application.
 */
public class EventCommand extends Command {
    /** Command word of the event command. */
    public static final String COMMAND_WORD = "event";
    private static final Pattern ARGUMENTS_FORMAT =
        Pattern.compile("(?<description>.+)\\s+/at\\s+(?<date>\\d{4}-\\d{2}-\\d{2}\\s+\\d{4})");
    private static final String USER_MESSAGE_FORMAT = "*beep* I've added this event for you!\n"
            + "  %s\n"
            + "Now you have %d tasks!";
    private final Event event;

    /**
     * Constructs an event command with arguments.
     *
     * @param arguments Arguments string is to be of the format "description /at YYYY-MM-DD".
     * @throws DukeException Exception due to invalid arguments.
     */
    public EventCommand(String arguments) throws DukeException {
        Matcher matcher = ARGUMENTS_FORMAT.matcher(arguments);
        String description;
        String date;
        if (matcher.matches()) {
            description = matcher.group("description");
            date = matcher.group("date");
        } else {
            throw new WrongEventFormatException();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }

        event = new Event(description, localDateTime);
    }

    @Override
    public CommandResult execute() {
        assert tasks != null : "Should setData() before calling execute().";
        tasks.addTask(event);
        TaskUndo undoAction = new TaskUndo(event);
        int numberOfTasks = tasks.size();
        String userMessage = String.format(USER_MESSAGE_FORMAT, event, numberOfTasks);
        return new CommandResult(userMessage, CommandResult.Action.UPDATE_FILE, undoAction);
    }
}
