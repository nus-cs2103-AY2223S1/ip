package duke.command;

import duke.*;
import duke.exceptions.CannotSortException;
import duke.exceptions.DukeException;

public class SortCommand extends Command {

    private TypeOfTask typeOfTask;
    private Order order;
    private boolean isChronological;

    public SortCommand(TypeOfTask typeOfTask, Order order, boolean isChronological ) {
        this.typeOfTask = typeOfTask;
        this.order = order;
        this.isChronological = isChronological;
    }

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
