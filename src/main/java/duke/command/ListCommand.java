package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * ListCommand prints out all tasks in tasks list.
 */
public class ListCommand extends Command {

    /**
     * Constructor for ListCommand.
     */
    public ListCommand() {
        super();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int numOfTasks = tasks.getSize();
        if (numOfTasks == 0) {
            return "You do not have any tasks in your list right now.";
        } else {
            StringBuilder str = new StringBuilder();
            str.append(String.format("Here %s the task%s in your list:\n",
                    numOfTasks > 1 ? "are" : "is", numOfTasks == 1 ? "" : "s"));
            for (int i = 1; i <= numOfTasks; i++) {
                str.append(String.format("%s. %s\n", String.valueOf(i), tasks.getTask(i).toString()));
            }
            return str.toString();
        }
    }
}
