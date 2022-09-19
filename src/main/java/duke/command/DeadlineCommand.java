package duke.command;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.inputoutput.DukeIo;
import duke.task.Deadline;
import duke.task.Task;
import duke.util.ParsedData;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * A DataCommand type command to indicate deadline
 */
public class DeadlineCommand extends DataCommand {

    private static final String ADD_TASK = "More task for you:%n" + "  %s%n" + "Now you have %d tasks in the list.";

    private static final String NO_DATETIME_FOUND =
            "I don't see any time stamps. \n" + "Use MMM d yyyy, dd/MM/yyyy, dd-MM-yyyy if you need to include a time";

    /**
     * Creates a instance of the deadline command.
     *
     * @param d ParsedData from the command input
     */
    public DeadlineCommand(ParsedData d) {
        super(d);
    }

    /**
     * {@inheritDoc} Adds a deadline task to the tasklist and prints it.
     *
     * @throws DukeException Thrown when invalid/missing data
     * @throws IOException Thrown when saving of data failed
     */
    @Override
    public void execute(TaskList tasks, DukeIo io, Storage storage) throws DukeException, IOException {
        Task task = Deadline.createDeadline(data);
        if (!task.containsDatetime()) {
            io.printTask(NO_DATETIME_FOUND);
        }
        tasks.addEntry(task);
        io.printTask(String.format(ADD_TASK, task, tasks.getSize()));
        storage.saveTask(task);
    }

}
