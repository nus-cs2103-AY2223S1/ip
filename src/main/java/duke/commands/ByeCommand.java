package duke.commands;

import java.io.IOException;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        super.setExit();
        try {
            tasks.writeToFile(storage);
            ui.goodbye();
        } catch (IOException e) {
            ui.saveFail();
        }
    }
}
