package duke;

/**
 * Stores the index of the task to be deleted from TaskList.
 */
public class DeleteCommand extends Command {
    int indexToDelete;

    /**
     * @param number 1 based indexing for the task to be deleted.
     */
    DeleteCommand(int number) {
        this.indexToDelete = number - 1;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("duke.Task deleted: " + tasks.remove(indexToDelete));
    }

    @Override
    boolean isExit() {
        return false;
    }
}
