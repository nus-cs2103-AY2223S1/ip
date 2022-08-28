package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Represents Command to delete any kind of task.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates delete command.
     * @param index Index of task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes task and prints to user.
     * Also saves the updated tasks to storage.
     * @param tasks List of tasks.
     * @param ui Ui interface for input and output.
     * @param storage Storage for Duke's file operations.
     * @return Duke's response
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.deleteTask(index);
        storage.saveFile(tasks);

        return String.join(
                "\n",
                "Noted. I've removed this task:",
                "  " + task,
                ui.getTaskCount(tasks.taskCount())
        );
    }
}
