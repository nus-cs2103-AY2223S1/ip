package duke.chatbot.command;

import static duke.chatbot.common.Message.MESSAGE_ADDED_TASK;

import java.util.ArrayList;
import java.util.List;

import duke.chatbot.data.task.ToDo;

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
     */
    @Override
    public CommandResult execute() {
        List<String> message = new ArrayList<>();
        ToDo task = new ToDo(arguments.get(0));
        taskList.add(task);
        message.add(MESSAGE_ADDED_TASK);
        message.add(task.toString());
        return new CommandResult(message);
    }
}
