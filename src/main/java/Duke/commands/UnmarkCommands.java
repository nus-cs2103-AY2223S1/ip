package Duke.commands;

import Duke.*;
import java.io.IOException;


public class UnmarkCommands extends TaskCommands {


    public UnmarkCommands(String input) throws DukeException {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (tasks.isValidTaskNumber(taskNumber)) {
            System.out.println("I've marked this task as not done (yet ;))");
            tasks.markAsNotDone(taskNumber);
            storage.updateTask(taskNumber, Constants.UNMARK);
            ui.printLine(tasks.getTaskToString(taskNumber));
        } else {
            throw new DukeException("tasks.Task does not exist.");
        }
    }
}
