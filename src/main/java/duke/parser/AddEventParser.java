package duke.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.commands.tasks.AddEventCommand;
import duke.domain.Event;
import duke.domain.task.Task;
import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidTaskSpecificationException;
import duke.exceptions.ParseException;

/**
 * AddEventParser Class
 */
public class AddEventParser implements IParser<AddEventCommand> {
    private static final Pattern ADD_EVENT_COMMAND_FORMAT = Pattern.compile(
            String.format("(?<title>.*)%s(?<subArgs>.*)", AddEventCommand.SUBCOMMAND_WORD));
    private Matcher matcher;

    @Override
    public AddEventCommand parse(String arguments) throws ParseException {
        matcher = ADD_EVENT_COMMAND_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new ParseException(
                    "Events need a at command");
        }
        final String eventTitle = matcher
                .group("title")
                .trim();
        final String eventSubArgs = matcher
                .group("subArgs")
                .trim();
        Task newEvent;
        try {
            newEvent = Task.of(
                    "E",
                    "0",
                    eventTitle,
                    eventSubArgs);
        } catch (InvalidDateTimeException | InvalidTaskSpecificationException e) {
            throw new ParseException(e.getMessage());
        }
        if (!(newEvent instanceof Event)) {
            throw new RuntimeException("This cannot be happening!");
        }
        Event castedNewEvent = (Event) newEvent;
        return new AddEventCommand(castedNewEvent);
    }

}
