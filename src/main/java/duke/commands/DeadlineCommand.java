package duke.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.exceptions.DukeException;
import duke.task.Deadline;

/**
 * Represents a deadline command in the Duke application.
 */
public class DeadlineCommand extends Command {
    /** Command word of the deadline command. */
    public static final String COMMAND_WORD = "deadline";
    private static final Pattern ARGUMENTS_FORMAT =
        Pattern.compile("(?<description>.+)\\s+/by\\s+(?<deadline>\\d{4}-\\d{2}-\\d{2}\\s+\\d{4})");
    private static final String USER_MESSAGE_FORMAT = "Added this deadline!\n  %s\nNow you have %d tasks.";
    private final Deadline deadline;

    /**
     * Constructor for a deadline command that takes in arguments.
     *
     * @param arguments Arguments string is to be of the format "description /by YYYY-MM-DD".
     * @throws DukeException Exception due to invalid arguments.
     */
    public DeadlineCommand(String arguments) throws DukeException {
        Matcher matcher = ARGUMENTS_FORMAT.matcher(arguments);
        String description;
        String deadline;
        if (matcher.matches()) {
            description = matcher.group("description");
            deadline = matcher.group("deadline");
        } else {
            throw Deadline.WRONG_FORMAT;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.parse(deadline, formatter);
        } catch (DateTimeParseException e) {
            throw DukeException.INVALID_DATE;
        }

        this.deadline = new Deadline(description, localDateTime);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute() {
        assert tasks != null : "Should setData() before calling execute().";
        tasks.addTask(deadline);
        int numberOfTasks = tasks.size();
        String userMessage = String.format(USER_MESSAGE_FORMAT, deadline, numberOfTasks);
        return new CommandResult(userMessage, true, false);
    }
}
