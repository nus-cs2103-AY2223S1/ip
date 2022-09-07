package command;

import tasklist.TaskList;
import util.Storage;
import util.Ui;

/**
 * Represents a task to be executed that deletes a specific task from internal
 * duke list based on given index.
 *
 * @author Bryan Lim Jing Xiang
 */
public class DeleteTaskCommand extends Command {
    private final int index;

    /**
     * @param index Index of the element to be deleted within the
     *              internal duke list
     */
    public DeleteTaskCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList list, Storage storage) {
        String deletedItem = "  " + list.deleteItem(index);
        String deleteText = "Noted. I've removed this task:";
        String endLine = String.format(
                "Now you have %d tasks in the list.",
                list.getTaskCount());
        setOutputMessage(Ui.formatLinesIntoParagraph(
                deleteText,
                deletedItem,
                endLine
        ));
    }
}
