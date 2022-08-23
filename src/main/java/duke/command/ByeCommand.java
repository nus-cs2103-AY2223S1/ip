package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {
        super();
        this.terminated = true;
    }

    @Override
    public void execCommand(TaskList list, Ui ui, Storage storage) {
        ui.showBye();
    }
}
