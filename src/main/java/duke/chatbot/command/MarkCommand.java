package duke.chatbot.command;

import duke.chatbot.data.task.Task;

import java.util.ArrayList;
import java.util.List;

import static duke.chatbot.common.Message.MESSAGE_MARKED;

public class MarkCommand extends Command {
    public MarkCommand(List<String> arguments) {
        this.arguments = arguments;
    }

    @Override
    public CommandResult execute() {
        List<String> message = new ArrayList<>();
        message.add(MESSAGE_MARKED);

        int entryNo = Integer.parseInt(arguments.get(0));
        Task task = taskList.get(entryNo);
        task.mark();
        message.add(task.toString());
        return new CommandResult(message);
    }
}
