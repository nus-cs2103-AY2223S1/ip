package tako.command;

import java.io.IOException;

import tako.Sort;
import tako.Storage;
import tako.TaskList;
import tako.Ui;

/**
 * Command to sort a task list.
 */
public class SortCommand extends Command {
    private Sort sortBy;
    private boolean isAscending;

    /**
     * Constructor for SortCommand with sort method and order.
     *
     * @param sortBy Method to sort using.
     * @param isAscending Boolean for sorted in ascending order.
     */
    public SortCommand(Sort sortBy, boolean isAscending) {
        this.sortBy = sortBy;
        this.isAscending = isAscending;
    }

    /**
     * Sorts the task list.
     *
     * @param tasks Task list.
     * @param ui Ui.
     * @param storage Task storage.
     * @throws IOException If the tasks fails to save to the file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.sort(sortBy, isAscending);
        storage.saveToFile(tasks);
        ui.showList(tasks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
