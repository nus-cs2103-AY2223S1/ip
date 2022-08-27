package commands;

import duke.Ui;
import tasks.Deadlines;
import tasks.Events;
import tasks.Task;
import tasks.TaskList;
import tasks.Todos;

public class ByeCommand extends Command {
    boolean toExitProgram = true;

    public ByeCommand() {
        super();
    }

    @Override
    public void run(TaskList taskList) {
        Ui.printByeStatement();;
    }
}
