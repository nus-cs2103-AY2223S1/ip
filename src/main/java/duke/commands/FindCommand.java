package duke.commands;

import java.util.ArrayList;

import duke.DukeException;
import duke.Message;
import duke.task.Task;
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
        ArrayList<Task> filteredTasks = tasks.filterByKeyword(this.keyword);
        if (filteredTasks.size() == 0) {
            throw new DukeException(MESSAGE_FAILURE);
        } else {
            return new Message(MESSAGE_SUCCESS + filteredTasks, false, Message.User.DUKE);
        }
    }
}
