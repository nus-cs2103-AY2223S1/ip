package duke.command;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidValueException;
import duke.task.Task;
import duke.util.DukeIo;
import duke.util.ParsedData;
import duke.util.Storage;
import duke.util.TaskList;

public class UnmarkCommand extends DataCommand {

    private static final String UNMARKED = "OK, I've marked this task as not done yet:%n  %s";

    UnmarkCommand(ParsedData data) {
        super(data);
    }

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
