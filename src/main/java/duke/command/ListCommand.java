package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage(tasks.toString());
    }

    @Override
    public String getCommand() {
        return "list";
    }

    @Override
    public String toString() {
        return "list";
    }
}
