package commands;

import duke.Ui;
import tasks.*;

public class ByeCommand extends Command {
    boolean toExitProgram = true;

    public ByeCommand() {
        super();
    }

    @Override
    public void run(TaskList taskList) {
        Ui.byeStatement();
    }
}
