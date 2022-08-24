package duke.command.handler;

import duke.command.CommandException;
import duke.command.response.CommandResponse;
import duke.data.TaskList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class CommandHandler {

    // yyyy-MM-dd[ HH:mm] (time is optional)
    // Example: 2022-08-23 12:11, 2022-08-23
    protected static final String commandDateTimeRegexStr = "(\\d{4}-\\d{2}-\\d{2}(\\s\\d{2}:\\d{2})?)";
    private static final DateTimeFormatter commandDateTimeFormatter = DateTimeFormatter.ofPattern(
        "yyyy-MM-dd[ HH:mm]");

    protected final String commandStr;
    protected final Pattern commandRegexPattern;
    protected final Matcher commandRegexMatcher;

    public CommandHandler(String commandStr, Pattern commandRegexPattern) throws CommandException {
        this.commandStr = commandStr;
        this.commandRegexPattern = commandRegexPattern;
        this.commandRegexMatcher = commandRegexPattern.matcher(commandStr);
        if (!isCommandValid()) {
            throw new CommandException(this.getInvalidFormatMessage());
        }
    }

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

    private boolean isCommandValid() {
        return commandRegexMatcher.find();
    }

    abstract protected String getInvalidFormatMessage();

    abstract public CommandResponse run(TaskList taskList) throws CommandException;
}
