package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Message;
import duke.ui.Ui;

/**
 * Represents an executable command to find a subset of tasks based on query.
 */
public class FindCommand extends Command {
    private String query;

    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Searches a task list for the query and returns a string listing those tasks.
     *
     * @param tasks TaskList object to filter.
     * @param storage Storage object to manage save file.
     * @return String representing a filtered task list.
     * @throws DukeException If the filtered list is empty.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        TaskList filtered = tasks.filter(this.query);
        if (filtered.getSize() == 0) {
            throw new DukeException(Message.NO_RESULTS_FOUND);
        }
        return Ui.getTaskListString(filtered);
    }
}
