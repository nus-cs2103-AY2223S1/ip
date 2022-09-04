package duke.chatbot.command;

import static duke.chatbot.common.Message.MESSAGE_INVALID_ARGUMENT;
import static duke.chatbot.common.Message.MESSAGE_MARKED;
import static duke.chatbot.common.Message.MESSAGE_OUT_OF_LIST_RANGE;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.task.Task;
import duke.chatbot.util.MessageBuilder;

/**
 * A command that marks the targeted task from the list of Task in the application. The targeted is chosen by an
 * argument string with an integer in the argument list.
 *
 * @author jq1836
 */
public class MarkCommand extends Command {
    /**
     * The command word to invoke this command
     */
    public static final String COMMAND_WORD = "mark";

    /**
     * The pattern for single integer arguments.
     */
    private static final Pattern SINGLE_INTEGER_ARGUMENT_FORMAT = Pattern.compile(
            "\\d+"
    );

    public MarkCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Returns an instance of {@link CommandResult} with a message that displays the task marked.
     *
     * @return The result after executing the command.
     */
    @Override
    public CommandResult execute() throws InvalidInputException {
        Matcher matcher = SINGLE_INTEGER_ARGUMENT_FORMAT.matcher(arguments);
        if (!matcher.matches()) {
            throw new InvalidInputException(MESSAGE_INVALID_ARGUMENT);
        }
        MessageBuilder message = new MessageBuilder();
        int entryNo = Integer.parseInt(matcher.group());
        Task task = taskList.get(entryNo);
        if (task == null) {
            throw new InvalidInputException(MESSAGE_OUT_OF_LIST_RANGE);
        }
        task.markDone();
        message.buildLines(MESSAGE_MARKED, task.toString());
        return new CommandResult(message.toString());
    }
}
