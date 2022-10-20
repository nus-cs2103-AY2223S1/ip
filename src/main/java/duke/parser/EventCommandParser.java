package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.DukeException;
import duke.Event;
import duke.commands.AddCommand;
import duke.commands.Command;

public class EventCommandParser implements Parser {
    private static final Pattern BASIC_ARGUMENTS_FORMAT =
            Pattern.compile("(?<description>\\S+)\\s/at\\s(?<atDate>.*)");

    @Override
    public Command parse(String arguments) throws DukeException {
        try {
            Matcher matcher = BASIC_ARGUMENTS_FORMAT.matcher(arguments.trim());
            if (!matcher.matches()) {
                throw new DukeException("Please add a description and a date for the event");
            }

            String description = matcher.group("description").trim();
            LocalDate atDate = LocalDate.parse(matcher.group("atDate").trim());

            Event event = new Event(description, atDate);

            return new AddCommand(event);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please provide a date in the form YYYY-MM-DD");
        }
    }
}
