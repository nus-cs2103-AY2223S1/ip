package duke.commands;

import duke.entities.Task;
import duke.enums.Messages;
import duke.exceptions.DukeException;
import duke.lists.TaskList;

import java.io.IOException;

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
    public void execute() throws DukeException, IOException {
        wrapWithLines(Messages.FOUND.toString(), tasks.find(keyword));
    }
}
