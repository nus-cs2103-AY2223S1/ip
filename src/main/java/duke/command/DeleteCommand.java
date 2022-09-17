package duke.command;

import java.util.ArrayList;
import java.util.Comparator;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents Command to delete any kind of task.
 */
public class DeleteCommand extends Command {
    public static final String ERROR_NO_ARGUMENTS = "No indexes given to delete?";

    private ArrayList<Integer> indexes;

    /**
     * Creates delete command.
     *
     * @param indexes Indexes of tasks to be deleted.
     */
    public DeleteCommand(ArrayList<Integer> indexes) {
        this.indexes = indexes;
    }

    /**
     * Deletes task and prints to user.
     * Also saves the updated tasks to storage.
     *
     * @param tasks List of tasks.
     * @param ui Ui interface for input and output.
     * @param storage Storage for Duke's file operations.
     * @return Duke's response.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (indexes.size() == 0) {
            throw new DukeException(DeleteCommand.ERROR_NO_ARGUMENTS);
        }

        if (indexes.stream().anyMatch(index -> index < 1)) {
            throw new DukeException(TaskList.ERROR_INDEX_GIVEN_TOO_SMALL);
        }

        if (indexes.stream().anyMatch(index -> index > tasks.taskCount())) {
            throw new DukeException(TaskList.ERROR_FEWER_TASKS_THAN_INDEX);
        }

        // Sort indexes to delete in reverse order.
        // This guarantees correctness since deleting a higher index
        // does not change the index of a task with lower index.
        indexes.sort(Comparator.comparingInt(index -> -index));

        ArrayList<String> responseLines = new ArrayList<>();
        int currCount = 0;
        for (int index: indexes) {
            Task task = tasks.deleteTask(index);
            currCount++;
            responseLines.add(String.format("%d. %s", currCount, task));
        }
        storage.saveFile(tasks);

        return String.join(
                "\n",
                String.format("Noted. I've removed %d tasks.", currCount),
                String.join("\n", responseLines),
                ui.getTaskCount(tasks.taskCount())
        );
    }
}
