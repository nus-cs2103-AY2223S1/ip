package duke.command;

import java.util.ArrayList;
import java.util.stream.Collectors;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Command that finds a task by searching for a keyword.
 *
 * @author Bryan Ng Zi Hao
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param keyword The keyword in the task description.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Checks if the tasks contains the keyword.
     *
     * @param ui The ui used to deal with interactions with the user.
     * @param storage The storage used to store the data.
     * @param taskList The list of tasks to check from.
     * @throws DukeException If there is an execution error.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        ArrayList<Task> filteredTasks = taskList.getTasks().stream().filter(task -> task.contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
        if (filteredTasks.size() > 0) {
            System.out.println("\t Here are the matching tasks in your list:");
            for (int i = 0; i < filteredTasks.size(); i++) {
                System.out.println("\t " + (i + 1) + ". " + filteredTasks.get(i));
            }
        } else {
            System.out.println("\t You have no matching tasks!");
        }
    }

    /**
     * Checks if the command results in Duke to stop running.
     *
     * @return a boolean value.
     */
    @Override
    public boolean isRunning() {
        return true;
    }
}
