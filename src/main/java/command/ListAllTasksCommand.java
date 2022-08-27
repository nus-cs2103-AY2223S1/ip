package command;

import tasklist.TaskList;
import util.Storage;
import util.Ui;

/**
 * Represents a command to be executed that lists all current tasks in the
 * internal duke list.
 *
 * @author Bryan Lim Jing Xiang
 */
public class ListAllTasksCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList list, Storage storage) {
        System.out.println(Ui.formatParagraph(
                list.toString()
        ));
    }
}
