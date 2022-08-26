package duke.command;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.task.Event;
import duke.task.Task;
import duke.util.DukeIo;
import duke.util.ParsedData;
import duke.util.Storage;
import duke.util.TaskList;

public class EventCommand extends DataCommand {

    private static final String ADD_TASK = "Got it. I've added this task:%n"
            + "  %s%n"
            + "Now you have %d tasks in the list.";

    EventCommand(ParsedData d) {
        super(d);
    }

    @Override
    public void execute(TaskList tasks, DukeIo io, Storage storage) throws DukeException, IOException {
        Task task = Event.createEvent(data);
        tasks.addEntry(task);
        io.printTask(String.format(ADD_TASK, task, tasks.getSize()));
        storage.saveTask(task);
    }

}
