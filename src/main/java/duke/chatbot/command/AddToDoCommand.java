package duke.chatbot.command;

import duke.chatbot.common.MessageConstants;
import duke.chatbot.data.exception.InvalidInputException;
import duke.chatbot.data.task.ToDo;

import java.util.ArrayList;
import java.util.List;

/**
 * A command that adds an instance of ToDo to the list of tasks
 * stored in the Duke application instance.
 */
public class AddToDoCommand extends Command {
    public AddToDoCommand(List<String> arguments) {
        this.arguments = arguments;
    }

    /**
     * Adds an instance of ToDo to the list of tasks stored in
     * the Duke application instance and returns an instance of
     * CommandResult which contains the ToDo added.
     * @return The result after executing the command.
     * @throws InvalidInputException If arguments passed to Command is invalid.
     */
    @Override
    public CommandResult execute() {
        List<String> message = new ArrayList<>();
        ToDo task = new ToDo(arguments.get(0));
        taskList.add(task);

        message.add(MessageConstants.MESSAGE_ADDED_TASK);
        message.add(task.toString());
        return new CommandResult(message);
    }
}
