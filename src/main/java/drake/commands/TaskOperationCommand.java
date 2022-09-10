package drake.commands;

import drake.*;

import java.io.IOException;

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
     * @param tasks The task list before the command is executed.
     * @param ui Gives access to the UI of the program.
     * @param storage Gives access to local storage.
     * @throws IOException when there is an issue with the IO.
     * @throws DrakeException when there is inappropriate input or save file issues.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DrakeException, IOException {
        if (tasks.isValidTaskNumber(taskNumber)) {
            ui.printLine(tasks.getTaskToString(taskNumber));
        } else {
            throw new InvalidTaskNumberException();
        }
    }
}
