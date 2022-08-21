package duke.command;

import duke.FileStorage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

/**
 * Find the tasks that matches the query in the taskList.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    private String query;
    public FindCommand(String query) {
        this.query = query;
    }
    @Override
    public void execute(TaskList list, FileStorage storage, Ui ui) {
        ArrayList<Task> foundTasks = list.findTasks(query);
        ui.printFoundTasks(foundTasks);
    }
}
