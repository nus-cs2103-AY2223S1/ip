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
 * Represents a command given by the user to perform an operation on a task currently in the list.
 */
public abstract class TaskOperationCommand extends Command {

    protected final int taskNumber;

    /**
     * Constructor.
     *
     * @param fullInput The user input.
     * @throws IncompatibleCommandException when the user input does not contain a task number.
     */
    public TaskOperationCommand(String fullInput) throws IncompatibleCommandException {
        String[] commands = fullInput.split(" ");
        try {
            taskNumber = Integer.parseInt(commands[1]);
        } catch (NumberFormatException e) {
            throw new IncompatibleCommandException("Where's the number?");
        }
    }

    /**
     * Executes the command to perform an operation on a task currently in the list.
     *
     * @param tasks   The task list before the command is executed.
     * @param ui      Gives access to the UI of the program.
     * @param storage Gives access to local storage.
     * @return The list of replies.
     * @throws IOException    when there is an issue with the IO.
     * @throws DrakeException when there is inappropriate input or save file issues.
     */
    @Override
    public List<String> execute(TaskList tasks, Ui ui, Storage storage) throws DrakeException, IOException {
        if (tasks.isValidTaskNumber(taskNumber)) {
            ArrayList<String> reply = new ArrayList<>();
            reply.add(tasks.getTaskToString(taskNumber));
            return reply;
        } else {
            throw new InvalidTaskNumberException();
        }
    }
}
