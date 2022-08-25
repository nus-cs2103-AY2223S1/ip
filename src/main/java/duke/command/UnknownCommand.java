package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class UnknownCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String tryagain = "I'm sorry, but I do not understand your orders.";
        ui.printMessage(tryagain);
    }
}
