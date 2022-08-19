package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

import java.io.IOException;

public class ByeCommand extends Command {
    @Override
    public boolean isTerminal() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        storage.save(tasks);
        ui.showMessage("Bye! Hope to see you again soon!");
    }
}
