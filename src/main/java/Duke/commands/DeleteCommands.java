package Duke.commands;

import Duke.*;

import java.io.IOException;

public class DeleteCommands extends TaskCommands {

    public DeleteCommands(String fullInput) throws DukeException {
        super(fullInput);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (tasks.isValidTaskNumber(taskNumber)) {
            System.out.println("I've removed this task: ");
            ui.printLine(tasks.getTaskToString(taskNumber));
            tasks.removeTask(taskNumber);
            storage.updateTask(taskNumber, Constants.DELETE);
        } else {
            throw new DukeException("This task does not exists");
        }
    }
}
