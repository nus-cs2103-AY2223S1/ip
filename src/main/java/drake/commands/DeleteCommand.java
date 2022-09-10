package drake.commands;

import drake.*;

import java.io.IOException;

public class DeleteCommand extends TaskOperationCommand {

    public DeleteCommand(String fullInput) throws IncompatibleCommandException {
        super(fullInput);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DrakeException, IOException {
        if (tasks.isValidTaskNumber(taskNumber)) {
            System.out.println("I've removed this task: ");
            ui.printLine(tasks.getTaskToString(taskNumber));
            tasks.removeTask(taskNumber);
            storage.updateTask(taskNumber, CommandType.DELETE);
        } else {
            throw new InvalidTaskNumberException();
        }
    }
}
