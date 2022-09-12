package duke.command.handler.base;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.command.CommandException;
import duke.command.response.CommandResponse;
import duke.data.TaskList;

public abstract class CommandHandler {

    // yyyy-MM-dd[ HH:mm] (time is optional)
    // Example: 2022-08-23 12:11, 2022-08-23
    protected static final String COMMAND_DATETIME_REGEX_STRING = "(\\d{4}-\\d{2}-\\d{2}(\\s\\d{2}:\\d{2})?)";
    private static final DateTimeFormatter commandDateTimeFormatter = DateTimeFormatter.ofPattern(
        "yyyy-MM-dd[ HH:mm]");

    protected final String commandStr;
    protected final Pattern commandRegexPattern;
    protected final Matcher commandRegexMatcher;

    /**
     * Constructor for class CommandHandler.
     *
     * @param commandStr input command string.
     * @param commandRegexPattern regex pattern for command string validation.
     * @throws CommandException if the input command string fails validation check.
     */
    public CommandHandler(String commandStr, Pattern commandRegexPattern) throws CommandException {
        this.commandStr = commandStr;
        this.commandRegexPattern = commandRegexPattern;
        this.commandRegexMatcher = commandRegexPattern.matcher(commandStr);
        if (!isCommandValid()) {
            throw new CommandException(this.getInvalidFormatMessage());
        }
    }

    /**
     * Parses a date-time string to LocalDateTime object.
     *
     * @param dateTimeStr date-time string.
     * @return the parsed local date-time.
     * @throws CommandException if date-time string cannot be parsed.
     */
    protected static LocalDateTime parseDateTime(String dateTimeStr) throws CommandException {
        try {
            TemporalAccessor temporalAccessor = commandDateTimeFormatter.parseBest(dateTimeStr,
                LocalDateTime::from, LocalDate::from);
            return (temporalAccessor instanceof LocalDateTime) ? (LocalDateTime) temporalAccessor
                : ((LocalDate) temporalAccessor).atStartOfDay();
        } catch (DateTimeParseException dateTimeParseException) {
            throw new CommandException("Invalid date time passed to command!");
        }
    }

    /**
     * Checks if the input command string is valid based on predefined regex.
     *
     * @return true if given input command string to CommandHandler is valid else false.
     */
    private boolean isCommandValid() {
        return commandRegexMatcher.find();
    }

    /**
     * Returns the error message for a command with invalid format.
     *
     * @return invalid format error message.
     */
    protected abstract String getInvalidFormatMessage();

    public abstract CommandResponse run(TaskList taskList) throws CommandException;
}
