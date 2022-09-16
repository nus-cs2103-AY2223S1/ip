package duke;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;

import duke.Duke.Command;
import duke.exception.DukeInvalidCommandException;

/**
 * Represents the command-parsing component of MakiBot.
 *
 * @author Justin Peng
 */
public class Parser {
    /** Datetime formatter for user input which accepts dd/MM/yyyy or dd/MM/yyyy HH:mm */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("dd/MM/yyyy ")
            .optionalStart()
            .appendPattern("HH:mm ")
            .optionalEnd()
            .appendZoneText(TextStyle.SHORT)
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .toFormatter();

    /**
     * Parses the input into a command and parameters.
     *
     * @param input The input {@code String} to be parsed.
     * @return A length-2 {@code String} array with the command as the first element
     *     and the parameters as the second element.
     */
    protected String[] parseFullCommand(String input) {
        return input.split(" ", 2);
    }

    /**
     * Obtains the {@code Command} from the input.
     * If the input does not match any {@code Command}, returns {@code null}.
     *
     * @param input The input {@code String} to be parsed.
     * @return The {@code Command} in the input.
     */
    protected Command parseCommand(String input) throws DukeInvalidCommandException {
        String[] fullCommand = parseFullCommand(input);
        String command = fullCommand[0].toUpperCase();
        if (!Command.contains(command)) {
            throw new DukeInvalidCommandException();
        }
        return Command.valueOf(command);
    }

    /**
     * Parses the input string into an instance of {@code ZonedDateTime}.
     *
     * @param input The input {@code String} to be parsed.
     * @param timeZone The timezone of the input.
     * @return The {@code ZonedDateTime} object.
     */
    protected ZonedDateTime parseDateTime(String input, ZoneId timeZone) {
        return ZonedDateTime.parse(input + " " + timeZone, DATE_TIME_FORMATTER);
    }

    /**
     * Parses the input string into an instance of {@code Duration}.
     *
     * @param input The input {@code String} to be parsed.
     * @return The {@code Duration} object.
     */
    protected Duration parseDuration(String input) {
        // TODO: Implement parseDuration
        return Duration.ofHours(Integer.parseInt(input));
    }
}
