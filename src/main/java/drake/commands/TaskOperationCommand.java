package drake.commands;

import drake.*;

import java.io.IOException;

public abstract class TaskOperationCommand extends Command {

    protected final int taskNumber;

    public TaskOperationCommand(String fullInput) throws IncompatibleCommandException {
        String[] commands = fullInput.split(" ");
        try {
            taskNumber = Integer.parseInt(commands[1]);
        } catch (NumberFormatException e) {
            throw new IncompatibleCommandException("Where's the number?");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DrakeException, IOException {
        if (tasks.isValidTaskNumber(taskNumber)) {
            ui.printLine(tasks.getTaskToString(taskNumber));
        } else {
            throw new InvalidTaskNumberException();
        }
    }
}
