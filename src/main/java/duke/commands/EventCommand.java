package duke.commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exceptions.DukeException;
import duke.task.Event;

/**
 * Represents an event command in the Duke application.
 */
public class EventCommand extends Command {
    /** Command word of the event command. */
    public static final String COMMAND_WORD = "event";
    private static final Pattern ARGUMENTS_FORMAT =
        Pattern.compile("(?<description>.+)\\s+/at\\s+(?<date>\\d{4}-\\d{2}-\\d{2})");
    private static final String userMessageFormat = "Added this event!\n  %s\nNow you have %d tasks.";
    private final Event event;

    /**
     * Constructor for an event command that takes in arguments.
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
            throw Event.WRONG_FORMAT;
        }

        LocalDate localDate;
        try {
            localDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw DukeException.INVALID_DATE;
        }

        this.event = new Event(description, localDate);
    }

    /**
     * Executes the command.
     *
     * @return Result of the execution.
     */
    @Override
    public CommandResult execute() {
        this.tasks.addTask(this.event);
        int numberOfTasks = this.tasks.size();
        String userMessage = String.format(userMessageFormat, this.event, numberOfTasks);
        return new CommandResult(userMessage, true, false);
    }
}
