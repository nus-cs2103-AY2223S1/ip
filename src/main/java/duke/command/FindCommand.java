package duke.command;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.DukeException;
import duke.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to find tasks from the keyword given by user.
 *
 * @author Derrick Khoo
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a command to find tasks from the keyword given by the user.
     *
     * @param keyword the keyword from the input given by user
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Handles a command to find tasks from the keyword given by user
     *
     * @param storage  the <code>Storage</code> that loads and saves tasks in a list of tasks to a file
     * @param ui       the user interface that the user sees
     * @param taskList the list of tasks
     * @throws DukeException if there is an error finding the tasks from the keyword given by user
     */
    @Override
    public void handle(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        ArrayList<Task> list = taskList.getTaskList();
        List<Task> filteredList = list.stream().filter(task -> task.containsKeyword(keyword))
                .collect(Collectors.toList());
        if (filteredList.size() == 0) {
            throw new DukeException("Sorry! You don't have tasks related to this keyword you entered");
        }
        int i = 0;
        ui.line();
        System.out.println("Here are all your related tasks:");
        for (Task t : filteredList) {
            System.out.println(i + 1 + "." + t);
            i++;
        }
        ui.line();
    }
}
