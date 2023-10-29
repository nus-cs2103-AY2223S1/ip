package zeus.command;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import zeus.Ui;
import zeus.ZeusException;
import zeus.storage.Storage;
import zeus.task.Task;
import zeus.task.TaskList;

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
     * @throws ZeusException if there is an error finding the tasks from the keyword given by user
     */
    @Override
    public String handle(Storage storage, Ui ui, TaskList taskList) throws ZeusException {
        ArrayList<Task> list = taskList.getTaskList();
        List<Task> filteredList = list.stream()
                .filter(task -> task.containsKeyword(keyword))
                .collect(Collectors.toList());
        if (filteredList.size() == 0) {
            throw new ZeusException("Sorry! You don't have tasks related to this keyword you entered");
        }
        String output = "Zeus says:\n" + "Here are all your related tasks:\n";
        int i = 0;
        for (Task t : filteredList) {
            output += i + 1 + "." + t + "\n";
            i++;
        }
        return output;
    }
}
