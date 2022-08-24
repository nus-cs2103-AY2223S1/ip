package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.byeMessage();
        ui.printDivider();
    }

    @Override
    public String getCommand() {
        return "exit";
    }

    @Override
    public String toString() {
        return "exit";
    }
}
