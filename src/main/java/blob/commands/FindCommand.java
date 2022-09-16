package blob.commands;

import java.util.ArrayList;
import java.util.List;

import blob.common.Messages;

/**
 * The DeadlineCommand class represents the command to find tasks based on a keyword.
 */
public class FindCommand extends TaskCommand {
    /** The keyword to find tasks for */
    private String keyword;

    /**
     * Returns a command that when executed will attempt to find tasks that have descriptions
     * matching the input keyword.
     *
     * @param keyword The input keyword.
     */
    public FindCommand(String keyword) {
        super("find");
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     */
    public CommandResult execute() {
        String[] taskList = this.taskList.findTasks(keyword);
        if (taskList.length == 0) {
            return new CommandResult(Messages.MESSAGE_NO_TASKS_FOUND);
        } else {
            ArrayList<String> messages = new ArrayList<>();
            messages.add(String.format(Messages.MESSAGE_TASKS_FOUND, taskList.length));
            messages.addAll(List.of(taskList));
            return new CommandResult(messages.toArray(new String[0]));
        }

    }
}
