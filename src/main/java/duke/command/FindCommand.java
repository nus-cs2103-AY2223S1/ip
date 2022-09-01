package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

/**
 * FindCommand is a command to find tasks with the matching keyword.
 */
public class FindCommand extends Command {

    private String keyword;
    private ArrayList<Task> matchingTasks;

    /**
     * Constructor for FindCommand.
     *
     * @param keyword Word to find in task.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
        this.matchingTasks = new ArrayList<>();
    }

    /**
     * Executes the specific command corresponding to the type of input the user gives.
     *
     * @param list List of tasks.
     * @param ui Ui to print messages.
     * @param storage To save the list after making changes.
     * @return String that matches the command input.
     */
    @Override
    public String execCommand(TaskList list, Ui ui, Storage storage) {
        for (int i = 0; i < list.getSize(); i++) {
            Task task = list.getTask(i);
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return ui.showFind(matchingTasks);
    }

}
