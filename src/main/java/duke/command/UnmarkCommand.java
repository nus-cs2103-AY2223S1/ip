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
 * Unmarks a given task based on index.
 */
public class UnmarkCommand extends DataCommand {

    private static final String UNMARKED = "OK, I've marked this task as not done yet:%n  %s";

    /**
     * Passes the index of the task
     * 
     * @param data ParsedData's description contains the index.
     */
    public UnmarkCommand(ParsedData data) {
        super(data);
    }

    /**
     * {@inheritDoc}
     * Unmarks the task with the given index as complete.
     * 
     * @throws DukeException Thrown when invalid index is given or out range
     * @throws IOException   Thrown when data failed to save
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

        task.unmark();
        io.printTask(String.format(UNMARKED, task));
        storage.saveTasks(tasks);
    }

}
