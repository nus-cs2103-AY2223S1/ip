package duke.commands;

import java.io.IOException;

import duke.enums.Messages;
import duke.exceptions.DukeException;
import duke.lists.TaskList;

/**
 * Search for a task that cotnains the given keyword
 */
public class FindCommand extends DisplayCommand {
    private TaskList tasks;
    private String keyword;

    /**
     * Initialises the command with the task list and  the keyword to search
     * @param tasks to search from
     * @param keyword to search for
     */
    public FindCommand(TaskList tasks, String keyword) {
        this.tasks = tasks;
        this.keyword = keyword;
    }

    @Override
    public String execute() throws DukeException, IOException {
        return wrapWithoutLines(Messages.FOUND.toString(), tasks.find(keyword));
    }
}
