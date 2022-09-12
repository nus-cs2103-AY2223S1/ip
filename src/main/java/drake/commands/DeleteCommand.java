package drake.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import drake.DrakeException;
import drake.IncompatibleCommandException;
import drake.InvalidTaskNumberException;
import drake.Storage;
import drake.TaskList;
import drake.Ui;

/**
 * Represents a delete command.
 */
public class DeleteCommand extends TaskOperationCommand {

    public DeleteCommand(String fullInput) throws IncompatibleCommandException {
        super(fullInput);
    }

    @Override
    public List<String> execute(TaskList tasks, Ui ui, Storage storage) throws DrakeException, IOException {
        if (!tasks.isValidTaskNumber(taskNumber)) {
            throw new InvalidTaskNumberException();
        }

        ArrayList<String> reply = new ArrayList<>();
        reply.add("I've removed this task: ");
        reply.add(tasks.getTaskToString(taskNumber));
        tasks.removeTask(taskNumber);
        storage.updateTask(taskNumber, CommandType.DELETE);
        return reply;
    }
}
