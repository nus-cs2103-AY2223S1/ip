package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Encapsulates a command to find a task by a given keyword.
 */
public class FindCommand extends Command {
    /** Stores the keyword(s) to search by. */
    String keyword;

    /**
     * Constructor for FindCommand.
     * @param keyword search keywords
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList res = new TaskList();

        for (int i = 0; i < tasks.getLength(); i++) {
            Task tmp = tasks.getTask(i+1);
            if (tmp.getName().contains(keyword)) {
                res.addTask(tmp);
            }
        }

        return "Here are the matching tasks in your list:\n" +
            res.toString();
    }
}
