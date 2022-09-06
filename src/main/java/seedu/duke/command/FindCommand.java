package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import seedu.duke.task.Task;

public class FindCommand extends Command {
    private String searchString;

    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public void execute(TaskList list) throws DukeException {
        TaskList foundList = new TaskList();
        for (int i = 0; i < list.size(); i++) {
            Task curr = list.get(i);
            if (curr.getName().contains(searchString)) {
                foundList.add(curr);
            }
        }
        if (!foundList.isEmpty()) {
            Ui.found(searchString, foundList);
        } else {
            Ui.notFound(searchString);
        }
    }
}
