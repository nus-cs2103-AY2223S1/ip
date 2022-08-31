package duke.chatbot.command;

import static duke.chatbot.common.Message.MESSAGE_ADDED_TASK;

import java.util.List;

import duke.chatbot.data.task.ToDo;
import duke.chatbot.util.MessageBuilder;

/**
 * A command that adds an instance of ToDo to the list of tasks
 * stored in the Duke application instance.
 * @author jq1836
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
     */
    @Override
    public CommandResult execute() {
        MessageBuilder message = new MessageBuilder();
        ToDo task = new ToDo(arguments.get(0));
        taskList.add(task);

        message.addLines(MESSAGE_ADDED_TASK, task.toString());
        return new CommandResult(message.toString());
    }
}
