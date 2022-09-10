package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.TaskList;
import seedu.duke.Ui.Ui;
import seedu.duke.task.Task;

/**
 * Class for executing find command
 */
public class FindCommand extends Command {
    private String searchString;

    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    /**
     * Finds items in the list matching the keywords, and compiles them into a new
     * TaskList. Should be noted that the indices of the new list do not match
     * the indices on the initial list.
     * @param list
     * @throws DukeException
     */
    @Override
    public String execute(TaskList list) throws DukeException {
        TaskList foundList = new TaskList();
        for (int i = 0; i < list.size(); i++) {
            Task curr = list.get(i);
            if (curr.getName().contains(searchString)) {
                foundList.add(curr);
            }
        }
        if (!foundList.isEmpty()) {
            return Ui.found(searchString, foundList);
        } else {
            return Ui.notFound(searchString);
        }
    }
}
