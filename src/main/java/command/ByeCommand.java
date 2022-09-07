package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {

    public ByeCommand() {
        this.setExit();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return Ui.bye();
    }
}
