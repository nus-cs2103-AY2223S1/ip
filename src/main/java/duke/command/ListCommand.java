package duke.command;

import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.printMessage(tasks.toString());
    }
}