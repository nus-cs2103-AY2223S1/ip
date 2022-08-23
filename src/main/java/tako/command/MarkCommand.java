package tako.command;

import java.io.IOException;

import tako.Storage;
import tako.TakoException;
import tako.TaskList;
import tako.Ui;

import tako.task.Task;

/**
 * Command to mark a task.
 */
public class MarkCommand extends Command {
    private int taskNumber;

    /**
     * Constructor for MarkCommand with number of the task to mark.
     *
     * @param taskNumber Position of the task to mark in the task list.
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks a task in the task list.
     * The task list is saved after that.
     *
     * @param tasks Task list.
     * @param ui Ui.
     * @param storage Task storage.
     * @throws IOException If the task list fails to save to the file
     * @throws TakoException If the task to mark does not exist.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, TakoException {
        tasks.mark(taskNumber);
        Task task = tasks.get(taskNumber);
        storage.saveToFile(tasks);
        ui.showMark(task);
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
