package tako.command;

import java.io.IOException;

import tako.Storage;
import tako.TaskList;
import tako.Ui;

import tako.task.Task;

/**
 * Commmand to add a task.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor for AddCommand with task to add.
     *
     * @param task Task to add.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds and saves a task to the task list.
     *
     * @param tasks Task list.
     * @param ui Ui.
     * @param storage Task storage.
     * @throws IOException If the task fails to save to the file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.add(task);
        storage.saveToFile(task);
        ui.showAdd(task, tasks.getSize());
    }

    /**
     * Returns false as Tako cannot exit after this command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
