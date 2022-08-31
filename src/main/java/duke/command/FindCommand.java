package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * FindCommand finds any tasks that contain the search keyword.
 */
public class FindCommand extends Command {
    private String searchKeyword;

    /**
     * Constructor for FindCommand.
     * @param searchKeyword search keyword for tasks.
     */
    public FindCommand(String searchKeyword) {
        super();
        this.searchKeyword = searchKeyword;
    }

    /**
     * @inheritDoc
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList foundTasks = tasks.findTasks(searchKeyword);
        int numOfTasks = foundTasks.getSize();
        if (numOfTasks == 0) {
            return "There are no matching tasks in your list.";
        } else {
            StringBuilder str = new StringBuilder();
            str.append(String.format("Here %s the task%s in your list:\n",
                    numOfTasks > 1 ? "are" : "is", numOfTasks == 1 ? "" : "s"));
            for (int i = 1; i <= numOfTasks; i++) {
                str.append(String.format("%s. %s\n", String.valueOf(i), foundTasks.getTask(i).toString()));
            }
            return str.toString();
        }
    }
}
