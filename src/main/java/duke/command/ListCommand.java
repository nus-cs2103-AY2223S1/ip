package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;


public class ListCommand extends Command{
    private int index;

    public ListCommand() {
        super();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMsg(tasks.enumerate());
    }
}