package duke.chatbot.command.AddTaskCommands;

import static duke.chatbot.common.Message.MESSAGE_INVALID_ARGUMENT;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.task.Deadline;
import duke.chatbot.data.task.Task;
import duke.chatbot.util.Parser;

/**
 * A command that adds an instance of {@link Deadline} to the list of tasks stored in the Duke application instance.
 *
 * @author jq1836
 */
public class AddDeadlineCommand extends AddTaskCommand {
    /**
     * The command word to invoke this command.
     */
    public static final String COMMAND_WORD = "deadline";

    /**
     * The pattern for deadline arguments.
     */
    private static final Pattern ADD_DEADLINE_ARGUMENT_FORMAT = Pattern.compile(
            "(?<description>.*)\\s/by\\s(?<dateTime>.*)"
    );

    public AddDeadlineCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    protected Task supplyTask() throws InvalidInputException {
        Matcher matcher = ADD_DEADLINE_ARGUMENT_FORMAT.matcher(arguments);
        if (!matcher.matches()) {
            throw new InvalidInputException(MESSAGE_INVALID_ARGUMENT);
        }
        String description = matcher.group("description").strip();
        LocalDateTime dateTime = Parser.parseDateTime(matcher.group("dateTime").strip());
        return new Deadline(description, dateTime);
    }
}
