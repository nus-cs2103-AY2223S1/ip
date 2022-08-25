package duke.command;

import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

    public ExitCommand(String info) {
        super(info);
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.showExitMessage();
    }
}
