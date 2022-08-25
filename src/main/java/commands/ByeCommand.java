package commands;

import java.util.ArrayList;
import tasks.*;
import duke.Statements;

public class ByeCommand extends Command {
    boolean toExitProgram = true;

    public ByeCommand() {
        super();
    }

    @Override
    public void run(ArrayList<Task> taskList) {
        Statements.byeStatement();
    }
}
