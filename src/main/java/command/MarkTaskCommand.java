package command;

import tasklist.TaskList;
import util.Storage;
import util.Ui;

/**
 * Represents a task to be executed that marks a specific task in the
 * internal duke list based on given index
 */
public class MarkTaskCommand extends Command {
    private final int index;

    /**
     * @param index Index of the element to be marked within the
     *              internal duke list
     */
    public MarkTaskCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList list, Storage storage) {
        String markedItem = "  " + list.markItem(index);
        String markedText = "Nice! I've marked this task as done:";
        setOutputMessage(Ui.formatLinesIntoParagraph(
                markedText,
                markedItem
        ));
    }
}
