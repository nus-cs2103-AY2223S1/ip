package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {
    public boolean isExit() {
        return true;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printWithIndent("Bye. Hope to see you again soon!");
    }
}
