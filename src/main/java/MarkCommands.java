import java.io.IOException;

public class MarkCommands extends TaskCommands {

    public MarkCommands(String input) throws DukeException {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (tasks.isValidTaskNumber(taskNumber)) {
            System.out.println("I've marked this task as done!");
            tasks.markAsDone(taskNumber);
            storage.updateTask(taskNumber, Constants.MARK);
            ui.printLine(tasks.getTaskToString(taskNumber));
        } else {
            throw new DukeException("This task does not exist.");
        }
    }
}
