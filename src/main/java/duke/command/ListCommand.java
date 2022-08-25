package duke.command;

import duke.processor.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks) {
        Ui ui = new Ui();
        ui.list(tasks);
    }
}
