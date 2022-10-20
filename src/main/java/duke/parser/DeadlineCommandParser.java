package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.Deadline;
import duke.DukeException;
import duke.commands.AddCommand;
import duke.commands.Command;

public class DeadlineCommandParser implements Parser {
    private static final Pattern BASIC_ARGUMENTS_FORMAT =
            Pattern.compile("(?<description>\\S+)\\s/by\\s(?<byDate>.*)");

    @Override
    public Command parse(String arguments) throws DukeException {
        try {
            Matcher matcher = BASIC_ARGUMENTS_FORMAT.matcher(arguments.trim());
            if (!matcher.matches()) {
                throw new DukeException("Please add a description and a date for the deadline");
            }

            String description = matcher.group("description").trim();
            LocalDate byDate = LocalDate.parse(matcher.group("byDate").trim());

            Deadline deadline = new Deadline(description, byDate);

            return new AddCommand(deadline);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please provide a date in the form YYYY-MM-DD");
        }
    }
}
