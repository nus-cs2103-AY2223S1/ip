package duke.chatbot.command;

import static duke.chatbot.common.Message.MESSAGE_INVALID_ARGUMENT;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.task.Event;
import duke.chatbot.util.Parser;

/**
 * A command that adds an instance of {@link Event} to the list of tasks stored in the Duke application instance.
 *
 * @author jq1836
 */
public class AddEventCommand extends AddTaskCommand {
    /**
     * The command word to invoke this command.
     */
    public static final String COMMAND_WORD = "event";

    /**
     * The pattern for event arguments.
     */
    private static final Pattern ADD_EVENT_ARGUMENT_FORMAT = Pattern.compile(
            "(?<description>.+?)\\s/at\\s(?<dateTime>)"
    );

    public AddEventCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Adds an instance of Event to the list of tasks stored in the Duke application instance and returns an instance
     * of {@link CommandResult} which contains the Event added.
     *
     * @return The result after executing the command.
     * @throws InvalidInputException If arguments passed to Command is invalid.
     */
    @Override
    public CommandResult execute() throws InvalidInputException {
        Matcher matcher = ADD_EVENT_ARGUMENT_FORMAT.matcher(arguments);
        if (!matcher.matches()) {
            throw new InvalidInputException(MESSAGE_INVALID_ARGUMENT);
        }
        String description = matcher.group("description").strip();
        LocalDateTime dateTime = Parser.parseDateTime(matcher.group("dateTime").strip());
        Event task = new Event(description, dateTime);
        addTask(task);
        return new CommandResult(buildMessage(task));
    }
}
