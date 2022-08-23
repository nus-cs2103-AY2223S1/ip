package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command{

    public static final String COMMAND_WORD = "bye";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.writeToFile(storage);
        super.setExit();
        ui.goodbye();
    }
}
