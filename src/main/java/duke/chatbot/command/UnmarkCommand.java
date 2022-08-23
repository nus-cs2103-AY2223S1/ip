package duke.chatbot.command;

import duke.chatbot.common.MessageConstants;
import duke.chatbot.data.task.Task;

import java.util.ArrayList;
import java.util.List;

public class UnmarkCommand extends Command {
    public UnmarkCommand(List<String> arguments) {
        this.arguments = arguments;
    }

    @Override
    public CommandResult execute() {
        List<String> message = new ArrayList<>();
        message.add(MessageConstants.MESSAGE_UNMARKED);

        int entryNo = Integer.parseInt(arguments.get(0));
        Task task = taskList.get(entryNo);
        task.unmark();
        message.add(task.toString());

        return new CommandResult(message);
    }
}
