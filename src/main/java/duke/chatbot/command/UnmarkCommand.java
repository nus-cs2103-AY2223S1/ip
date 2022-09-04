package duke.chatbot.command;

import static duke.chatbot.common.Message.MESSAGE_INVALID_ARGUMENT;
import static duke.chatbot.common.Message.MESSAGE_OUT_OF_LIST_RANGE;
import static duke.chatbot.common.Message.MESSAGE_UNMARKED;

import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.task.Task;
import duke.chatbot.util.MessageBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A command that unmarks the targeted task from the list of Task in the application. The targeted is chosen by an
 * argument string with an integer in the argument list.
 *
 * @author jq1836
 */
public class UnmarkCommand extends Command {
    /**
     * The command word to invoke this command
     */
    public static final String COMMAND_WORD = "unmark";

    /**
     * The pattern for single integer arguments.
     */
    private static final Pattern SINGLE_INTEGER_ARGUMENT_FORMAT = Pattern.compile(
            "\\d+"
    );

    public UnmarkCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Returns an instance of {@link CommandResult} with a message that displays the task unmarked.
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
        task.markUndone();
        message.buildLines(MESSAGE_UNMARKED, task.toString());
        return new CommandResult(message.toString());
    }
}
