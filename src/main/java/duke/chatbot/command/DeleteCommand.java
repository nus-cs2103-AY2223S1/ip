package duke.chatbot.command;

import duke.chatbot.common.MessageConstants;
import duke.chatbot.data.task.Task;

import java.util.ArrayList;
import java.util.List;

public class DeleteCommand extends Command {
    public DeleteCommand(List<String> arguments) {
        this.arguments = arguments;
    }

    @Override
    public CommandResult execute() {
        List<String> message = new ArrayList<>();
        message.add(MessageConstants.MESSAGE_DELETED);

        int entryNo = Integer.parseInt(arguments.get(0));
        Task task = taskList.remove(entryNo);
        message.add(task.toString());

        return new CommandResult(message);
    }
}
