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
 * A DataCommand that stores the index of which task to remove.
 */
public class DeleteCommand extends DataCommand {

    private static final String DELETE_TASK = "Noted. I've removed this task:%n"
            + "  %s%n"
            + "Now you have %d tasks in the list.";

    /**
     * Create an instance of Delete command.
     * 
     * @param d ParsedData containing the index of the task to delete
     */
    public DeleteCommand(ParsedData d) {
        super(d);
    }

    /**
     * {@inheritDoc}
     * Removes the task given by the index (description of parsed data).
     * 
     * @throws DukeException Thrown when invalid index is given
     * @throws IOException   Thrown when saving to file failed
     */
    @Override
    public void execute(TaskList tasks, DukeIo io, Storage storage) throws DukeException, IOException {
        int index;
        try {
            index = Integer.parseInt(data.description) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidValueException(data.command);
        }

        Task task = tasks.deleteEntry(index);
        io.printTask(String.format(DELETE_TASK, task, tasks.getSize()));
        storage.saveTask(task);
    }

}
