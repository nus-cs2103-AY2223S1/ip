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
        super();
        this.keyword = keyword;
        this.matchingTasks = new ArrayList<>();
    }

    @Override
    public void execCommand(TaskList list, Ui ui, Storage storage) {
        for (int i = 0; i < list.getSize(); i++) {
            Task task = list.getTask(i);
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        ui.showFind(matchingTasks);
    }

}
