package duke.commands;

import duke.DukeException;
import duke.Message;
import duke.task.TaskList;

/**
 * Command to search for a task given certain constraints.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_SUCCESS = "Here are the matching tasks in your list:\n";
    public static final String MESSAGE_FAILURE = "No tasks match the provided keyword.";

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public Message execute(TaskList tasks) throws DukeException {
        TaskList filteredTasks = tasks.filterByKeyword(this.keyword);
        if (filteredTasks.isEmpty()) {
            throw new DukeException(MESSAGE_FAILURE);
        }
        return new Message(MESSAGE_SUCCESS + filteredTasks, false, Message.User.DUKE);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FindCommand)) {
            return false;
        }
        FindCommand other = (FindCommand) obj;
        return this.keyword.equals(other.keyword);
    }

    @Override
    public int hashCode() {
        return this.keyword.hashCode();
    }
}
