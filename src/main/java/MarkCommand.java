import java.io.IOException;

public class MarkCommand extends TaskOperationCommand {

    public MarkCommand(String fullInput) throws IncompatibleCommandException {
        super(fullInput);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidTaskNumberException, IOException {
        if (tasks.isValidTaskNumber(taskNumber)) {
            System.out.println("I've marked this task as done!");
            tasks.markAsDone(taskNumber);
            storage.updateTask(taskNumber, CommandType.MARK);
            ui.printLine(tasks.getTaskToString(taskNumber));
        } else {
            throw new InvalidTaskNumberException();
        }
    }
}
