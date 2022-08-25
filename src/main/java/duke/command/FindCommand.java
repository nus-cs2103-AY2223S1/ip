package duke.command;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the command to find tasks based on a search query.
 */
public class FindCommand extends Command {
    private String searchQuery;

    /**
     * Creates FindCommand.
     * @param searchQuery String to filter description for.
     */
    public FindCommand(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    /**
     * Prints out tasks that have the search query in the description.
     * @param taskList List of tasks.
     * @param ui Ui interface for input and output.
     * @param storage Storage for Duke's file operations.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> filteredTaskList = taskList.searchTasks(searchQuery);
        ui.printWithIndent(String.format("Here are the tasks containing '%s':", searchQuery));
        for (int i = 0; i < filteredTaskList.size(); i++) {
            Task task = filteredTaskList.get(i);
            ui.printWithIndent(i + 1 + ". " + task);
        }
    }
}
