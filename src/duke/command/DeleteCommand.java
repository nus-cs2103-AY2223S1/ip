package duke.command;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidValueException;
import duke.task.Task;
import duke.util.DukeIo;
import duke.util.ParsedData;
import duke.util.Storage;
import duke.util.TaskList;

public class DeleteCommand extends DataCommand {

    private static final String DELETE_TASK = "Noted. I've removed this task:%n"
            + "  %s%n"
            + "Now you have %d tasks in the list.";

    DeleteCommand(ParsedData d) {
        super(d);
    }

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
