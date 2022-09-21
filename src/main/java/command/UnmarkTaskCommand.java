package command;

import tasklist.TaskList;
import util.Storage;
import util.Ui;

/**
 * Represents a task to be executed that unmarks a specific task within the
 * internal duke list based on given index
 *
 * @author Bryan Lim Jing Xiang
 */
public class UnmarkTaskCommand extends Command {
    private final int index;

    /**
     * @param index Index of the element to be unmarked within the
     *              internal duke list
     */
    public UnmarkTaskCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList list, Storage storage) {
        String unmarkedItem = "  " + list.unmarkItem(index);
        String unmarkedText = "OK, I've marked this task as not done yet:";
        setOutputMessage(Ui.formatLinesIntoParagraph(
                unmarkedText,
                unmarkedItem
        ));
    }
}
