package blob.commands;

import blob.common.Messages;

import java.util.ArrayList;
import java.util.List;

public class ListCommand extends TaskCommand {
    public ListCommand() {
        super("list");
    }

    public CommandResult execute() {
        ArrayList<String> messages = new ArrayList<>();
        messages.add(String.format(Messages.MESSAGE_TASK_LIST_SIZE, taskList.getNumberOfTasks()));
        messages.addAll(List.of(taskList.listTasks()));
        return new CommandResult(messages.toArray(new String[0]));
    }
}
