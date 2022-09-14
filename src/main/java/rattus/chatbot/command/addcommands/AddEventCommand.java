package rattus.chatbot.command.addcommands;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rattus.chatbot.data.exception.InvalidInputException;
import rattus.chatbot.data.task.Event;
import rattus.chatbot.data.task.Task;
import rattus.chatbot.util.Parser;
import rattus.chatbot.common.Message;

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
            "(?<description>.*)\\s/at\\s(?<dateTime>.*)"
    );

    public AddEventCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    protected Task supplyTask() throws InvalidInputException {
        Matcher matcher = ADD_EVENT_ARGUMENT_FORMAT.matcher(arguments);
        if (!matcher.matches()) {
            throw new InvalidInputException(Message.MESSAGE_INVALID_ARGUMENT);
        }
        String description = matcher.group("description").strip();
        LocalDateTime dateTime = Parser.parseDateTime(matcher.group("dateTime").strip());
        return new Event(description, dateTime);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof AddEventCommand;
    }
}
