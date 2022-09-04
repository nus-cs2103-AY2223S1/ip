package duke.chatbot.command;

import static duke.chatbot.common.Message.MESSAGE_MARKED;

import java.util.List;

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

    public MarkCommand(List<String> arguments) {
        this.arguments = arguments;
    }

    /**
     * Returns an instance of {@link CommandResult} with a message that displays the task marked.
     *
     * @return The result after executing the command.
     */
    @Override
    public CommandResult execute() {
        MessageBuilder message = new MessageBuilder();
        int entryNo = Integer.parseInt(arguments.get(0));
        Task task = taskList.get(entryNo);
        task.markDone();
        message.addLines(MESSAGE_MARKED, task.toString());
        return new CommandResult(message.toString());
    }
}
