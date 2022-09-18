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
 * Represents the mark command.
 */
public class MarkCommand extends TaskOperationCommand {

    public MarkCommand(String fullInput) throws IncompatibleCommandException {
        super(fullInput);
    }

    @Override
    public List<String> execute(TaskList tasks, Ui ui, Storage storage) throws DrakeException, IOException {
        if (tasks.isValidTaskNumber(taskNumber)) {
            ArrayList<String> reply = new ArrayList<>();
            reply.add("I've marked this task as done!");
            tasks.markAsDone(taskNumber);
            storage.updateTask(taskNumber, CommandType.MARK);
            reply.add(tasks.getTaskToString(taskNumber));
            return reply;
        } else {
            throw new InvalidTaskNumberException();
        }
    }
}
