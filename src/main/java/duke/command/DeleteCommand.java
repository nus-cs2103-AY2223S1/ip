package duke.command;

import duke.InvalidIndexException;
import duke.Storage;
import duke.task.Task;
import duke.TaskList;
import duke.Ui;

/**
 * DeleteCommand deletes a task.
 */
public class DeleteCommand extends Command {
    private int indexToDelete;

    /**
     * Constructor for DeleteCommand
     * @param indexToDelete index of task to delete.
     */
    public DeleteCommand(int indexToDelete) {
        super();
        this.indexToDelete = indexToDelete;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidIndexException {
        if (indexToDelete < 0 || indexToDelete > tasks.getSize()) {
            throw new InvalidIndexException();
        }
        Task taskToDelete = tasks.getTask(indexToDelete);
        tasks.removeFromTaskList(indexToDelete);
        storage.save(tasks.getTasks());
        return String.format("Noted. I've removed this task:\n" +
                "%s\n" +
                "Now you have %s task%s in the list.",
                taskToDelete.toString(),
                String.valueOf(tasks.getSize()),
                tasks.getSize() == 1 ? "" : "s");
    }
}
