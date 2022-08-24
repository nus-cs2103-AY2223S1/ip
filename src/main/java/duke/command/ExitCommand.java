package duke.command;

import duke.*;
import duke.exception.*;

import java.io.IOException;

public class ExitCommand extends Command{

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.out.println("Error saving file!!");
        }
    }
}
