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

    private String keyword;

    /**
     * Constructor for FindCommand.
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
            throw new DukeException("OOPS!!! No tasks match this keyword.");
        }
        message = "Here are the matching tasks in your list:" + System.lineSeparator();
        for (int i = 0; i < matchingTasks.getSize(); i++) {
            Task task = matchingTasks.get(i);
            message += (i + 1) + "." + task + System.lineSeparator();
        }
        return message;
    }
}
