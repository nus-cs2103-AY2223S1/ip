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
     * @param tasks List of tasks.
     * @param ui Ui interface for input and output.
     * @param storage Storage for Duke's file operations.
     * @return Duke's response
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> filteredTasks = tasks.searchTasks(searchQuery);
        ArrayList<String> responseLines = new ArrayList<>();
        responseLines.add(String.format("Here are the tasks containing '%s':", searchQuery));
        for (int i = 0; i < filteredTasks.size(); i++) {
            Task task = filteredTasks.get(i);
            responseLines.add(i + 1 + ". " + task);
        }
        return String.join("\n", responseLines);
    }
}
