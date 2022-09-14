package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.list.FoundList;
import seedu.duke.list.TaskList;
import seedu.duke.ui.Ui;
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
     * TaskList, retaining the indices.
     * @param list
     * @throws DukeException
     */
    @Override
    public String execute(TaskList list) throws DukeException {
        FoundList foundList = new FoundList(list);
        for (int i = 0; i < list.size(); i++) {
            Task curr = list.get(i);
            if (curr.getName().contains(searchString)) {
                foundList.add(i + 1);
            }
        }
        if (!foundList.isEmpty()) {
            return Ui.found(searchString, foundList);
        } else {
            return Ui.notFound(searchString);
        }
    }
}
