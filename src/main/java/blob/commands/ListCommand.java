package blob.commands;

import java.util.ArrayList;
import java.util.List;

import blob.common.Messages;

/**
 * The ListCommand class represents a command to list all the tasks that are currently active.
 */
public class ListCommand extends TaskCommand {

    /**
     * Returns a command that when executed will attempt to list the tasks in the task list.
     */
    public ListCommand() {
        super("list");
    }

    /**
     * {@inheritDoc}
     */
    public CommandResult execute() {
        ArrayList<String> messages = new ArrayList<>();
        messages.add(String.format(Messages.MESSAGE_TASK_LIST_SIZE, taskList.getNumberOfTasks()));
        messages.addAll(List.of(taskList.listTasks()));
        return new CommandResult(messages.toArray(new String[0]));
    }
}
