package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

import java.io.IOException;

public class ListCommand extends Command {
    @Override
    public boolean isTerminal() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        if (tasks.size() == 0) {
            ui.showMessage("You do not have any tasks at the moment.");
        } else {
            ui.showMessage("Here are your tasks.").showMessage(tasks.toString());
        }
    }
}
