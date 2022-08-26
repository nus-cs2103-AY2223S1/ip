package duke.command;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidValueException;
import duke.task.Task;
import duke.util.DukeIo;
import duke.util.ParsedData;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Command to mark a task given its index as complete.
 */
public class MarkCommand extends DataCommand {

    private static final String MARKED = "Nice! I've marked this task as done:%n  %s";

    /**
     * Passes in the index of which task to mark.
     * 
     * @param data ParsedData that contains index of the task.
     */
    public MarkCommand(ParsedData data) {
        super(data);
    }

    /**
     * {@inheritDoc} Marks the task with the given index as complete.
     * 
     * @throws DukeException Thrown when invalid index is given or out range
     * @throws IOException Thrown when data failed to save
     */
    @Override
    public void execute(TaskList tasks, DukeIo io, Storage storage) throws DukeException, IOException {
        int index;
        try {
            index = Integer.parseInt(data.description) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidValueException(data.command);
        }

        Task task = tasks.get(index);

        task.mark();
        io.printTask(String.format(MARKED, task));
        storage.saveTasks(tasks);
    }

}
