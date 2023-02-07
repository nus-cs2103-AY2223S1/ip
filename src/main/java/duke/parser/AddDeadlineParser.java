package duke.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.commands.tasks.AddDeadlineCommand;
import duke.domain.Deadline;
import duke.domain.task.Task;
import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidTaskSpecificationException;
import duke.exceptions.ParseException;

/**
 * AddDeadlineParser Class
 */
public class AddDeadlineParser implements IParser<AddDeadlineCommand> {
    private static final Pattern ADD_DEADLINE_COMMAND_FORMAT = Pattern.compile(
            String.format("(?<title>.*)%s(?<subArgs>.*)", AddDeadlineCommand.SUBCOMMAND_WORD));

    @Override
    public AddDeadlineCommand parse(String arguments) throws ParseException {
        Matcher matcher = ADD_DEADLINE_COMMAND_FORMAT.matcher(arguments.trim());
        if (!matcher.matches()) {
            throw new ParseException(
                    "Deadlines need a by command");
        }
        final String deadlineTitle = matcher
                .group("title")
                .trim();
        final String deadlineSubArgs = matcher
                .group("subArgs")
                .trim();
        Task newDeadline;
        try {
            newDeadline = Task.of(
                    "D",
                    "0",
                    deadlineTitle,
                    deadlineSubArgs);
        } catch (InvalidDateTimeException | InvalidTaskSpecificationException e) {
            throw new ParseException(e.getMessage());
        }
        if (!(newDeadline instanceof Deadline)) {
            throw new RuntimeException("This cannot be happening!");
        }
        Deadline castedNewDeadline = (Deadline) newDeadline;
        return new AddDeadlineCommand(castedNewDeadline);
    }
}
