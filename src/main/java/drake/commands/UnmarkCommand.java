package drake.commands;

import drake.*;

import java.io.IOException;

public class UnmarkCommand extends TaskOperationCommand {

    public UnmarkCommand(String fullInput) throws IncompatibleCommandException {
        super(fullInput);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DrakeException, IOException {
        if (tasks.isValidTaskNumber(taskNumber)) {
            System.out.println("I've marked this task as not done (yet ;))");
            tasks.markAsNotDone(taskNumber);
            storage.updateTask(taskNumber, CommandType.UNMARK);
            ui.printLine(tasks.getTaskToString(taskNumber));
        } else {
            throw new InvalidTaskNumberException();
        }
    }
}
