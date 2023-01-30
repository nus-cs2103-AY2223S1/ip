package duke.command;

import duke.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * A class to represent command to find tasks matching a search text.
 */
public class FindCommand extends Command {
    private String searchText;

    /**
     * Constructs a new FindCommand.
     *
     * @param storage  Storage to be used.
     * @param ui       Ui to be used.
     * @param taskList TaskList to be used.
     * @param input    User input command
     * @throws DukeException if search text not provided in input
     */
    public FindCommand(Storage storage, Ui ui, TaskList taskList, String input) throws DukeException {
        super(storage, ui, taskList);
        if (input.split(" ").length == 1) {
            throw new DukeException("Please enter a valid search text when finding tasks!");
        }
        this.searchText = input.split(" ")[1];
    }

    /**
     * Finds the tasks matching the search text and displays them.
     *
     * @return String representation of the tasks matching the search text
     */
    @Override
    public String execute() {
        return taskList.findTask(searchText);
    }
}
