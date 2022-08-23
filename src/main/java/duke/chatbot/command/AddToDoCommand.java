package duke.chatbot.command;

import duke.chatbot.common.MessageConstants;
import duke.chatbot.data.task.ToDo;

import java.util.ArrayList;
import java.util.List;

public class AddToDoCommand extends Command {
    public AddToDoCommand(List<String> arguments) {
        this.arguments = arguments;
    }

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
