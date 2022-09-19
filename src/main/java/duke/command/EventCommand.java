package duke.command;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.inputoutput.DukeIo;
import duke.task.Event;
import duke.task.Task;
import duke.util.ParsedData;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * A DataCommand makes a Event task
 */
public class EventCommand extends DataCommand {

    private static final String ADD_TASK =
            "Got it. I've added this:%n" + "  %s%n" + "Now you have %d tasks in the list.";

    private static final String NO_DATETIME_FOUND = "I don't see any time stamps. \n"
            + "Use formats like:\n MMM d yyyy\n dd/MM/yyyy\n dd-MM-yyyy \nif you need to include a time";

    /**
     * Creates a command that makes an Event when execute is called.
     *
     * @param d Parseddata containing parsed user input
     */
    public EventCommand(ParsedData d) {
        super(d);
    }

    /**
     * Creates a new Event task and prints it to screen.
     *
     * @throws DukeException Thrown when input data is invalid
     * @throws IOException Thrown when data failed to be saved
     */
    @Override
    public void execute(TaskList tasks, DukeIo io, Storage storage) throws DukeException, IOException {
        Task task = Event.createEvent(data);
        if (!task.containsDatetime()) {
            io.printTask(NO_DATETIME_FOUND);
        }
        tasks.addEntry(task);
        io.printTask(String.format(ADD_TASK, task, tasks.getSize()));
        storage.saveTask(task);
    }

}
