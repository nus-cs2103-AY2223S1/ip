package duke.command;

import duke.Ui;
import duke.processor.TaskList;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks) {
        Ui ui = new Ui();
        ui.list(tasks);
    }
}
