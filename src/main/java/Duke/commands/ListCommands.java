package Duke.commands;


import Duke.DukeException;
import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import java.io.IOException;

public class ListCommands extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        ui.printLine("Here are the tasks in your list:");
        for (int i = 1; tasks.isValidTaskNumber(i); i++) {
            ui.printLine(i + ". " + tasks.getTaskToString(i));
        }
    }
}
