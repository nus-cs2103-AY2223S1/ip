package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

public class ByeCommand extends Command {
    public ByeCommand(String instruction) {
        super(instruction);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print(ui.showExitMessage());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
