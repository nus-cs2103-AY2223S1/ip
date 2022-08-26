package duke;

import java.io.IOException;
import java.util.ArrayList;

public class ExitCommand extends Command{
    @Override
    void execute(String taskName, ArrayList<Task> listOfTasks, Ui ui, Storage storage) throws IOException {
        ui.bidFarewellUi();
        storage.updateFile();
    }
}
