package duke.command;

import duke.Order;
import duke.Storage;
import duke.TaskList;
import duke.TypeOfTask;
import duke.Ui;
import duke.exceptions.CannotSortException;

/**
 * Represents a Sort Command
 */
public class SortCommand extends Command {

    private TypeOfTask typeOfTask;
    private Order order;
    private boolean isChronological;

    /**
     * SortCommand that compose of typeOfTask, order
     * and isChronological
     *
     * @param typeOfTask typeOfTask.
     * @param order order.
     * @param isChronological if sorting is chronological.
     */
    public SortCommand(TypeOfTask typeOfTask, Order order, boolean isChronological) {
        this.typeOfTask = typeOfTask;
        this.order = order;
        this.isChronological = isChronological;
    }

    /**
     * Returns Ui output of successfully sorting the taskList
     *
     * @param ui ui of Duke.
     * @param taskList taskList to be sorted.
     * @param storage storage where task is stored.
     * @throws CannotSortException if taskList cannot be sorted.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) throws CannotSortException {
        if (isChronological) {
            TaskList sortedTaskList = taskList.sortChronologically(typeOfTask, order);
            return ui.showList(sortedTaskList);
        }
        TaskList sortedTaskList = taskList.sortLexicographically(typeOfTask, order);
        return ui.showList(sortedTaskList);
    }
}
