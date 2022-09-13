package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * FindCommand is the Command used to find tasks with a specific keyword.
 */
public class FindCommand extends Command {
    private static final String NO_MATCH = "OOPS!!! No tasks match this keyword.";
    private static final String HEADER_MSG = "Here are the matching tasks in your list:"
            + System.lineSeparator();
    private static final String SEPARATOR = ".";
    private String keyword;

    /**
     * Initializes a FindCommand object.
     *
     * @param keyword The specific word to search throughout the TaskList.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds a list of tasks that contains the keyword.
     *
     * @param tasks The list of tasks.
     * @param ui The class that deals with interactions with the user.
     * @param storage The class that deals with loading and storing tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String message;
        TaskList matchingTasks = new TaskList();
        for (int i = 0; i < tasks.getSize(); i++) {
            Task curr = tasks.get(i);
            if (curr.getDescription().contains(keyword)) {
                matchingTasks.add(curr);
            }
        }

        if (matchingTasks.getSize() == 0) {
            throw new DukeException(NO_MATCH);
        }
        message = HEADER_MSG;
        for (int i = 0; i < matchingTasks.getSize(); i++) {
            Task task = matchingTasks.get(i);
            message += (i + 1) + SEPARATOR + task + System.lineSeparator();
        }
        return message;
    }
}
