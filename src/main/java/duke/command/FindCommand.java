package duke.command;

import java.util.List;

import duke.DukeException;
import duke.task.Task;

/**
 * Command for finding tasks.
 */
public class FindCommand extends Command {
    private final String query;

    /**
     * Constructor for FindCommand.
     *
     * @param query Query string.
     */
    public FindCommand(String query) {
        super.isExit = false;
        this.query = query;
    }

    /**
     * Searches TaskList for tasks with description that contains {@code query} and
     * displays the matched tasks.
     */
    @Override
    public void execute() throws DukeException {
        List<Task> matched = Command.taskList.find(this.query);
        Command.ui.displayTaskListSearch(matched);
    }
}
