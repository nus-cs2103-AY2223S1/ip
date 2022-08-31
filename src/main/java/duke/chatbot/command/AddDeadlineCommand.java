package duke.chatbot.command;

import static duke.chatbot.common.Message.MESSAGE_ADDED_TASK;

import java.util.List;

import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.task.Deadline;
import duke.chatbot.util.MessageBuilder;
import duke.chatbot.util.Parser;

/**
 * A command that adds an instance of Deadline to the list of tasks
 * stored in the Duke application instance.
 * @author jq1836
 */
public class AddDeadlineCommand extends Command {
    public AddDeadlineCommand(List<String> arguments) {
        this.arguments = arguments;
    }

    /**
     * Adds an instance of Deadline to the list of tasks stored in
     * the Duke application instance and returns an instance of
     * CommandResult which contains the Deadline added.
     * @return The result after executing the command.
     * @throws InvalidInputException If arguments passed to Command is invalid.
     */
    @Override
    public CommandResult execute() throws InvalidInputException {
        MessageBuilder message = new MessageBuilder();
        Deadline task = new Deadline(arguments.get(0), Parser.parseDateTime(arguments.get(1)));
        taskList.add(task);

        message.addLines(MESSAGE_ADDED_TASK, task.toString());
        return new CommandResult(message.toString());
    }
}
