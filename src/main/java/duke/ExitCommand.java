package duke;

import java.io.IOException;
import java.util.ArrayList;

import static com.sun.javafx.application.PlatformImpl.exit;

public class ExitCommand extends Command {
    @Override
    String execute(String taskName, ArrayList<Task> listOfTasks, Ui ui, Storage storage) {
       try {
           exit();
           storage.updateFile();
           return ui.bidFarewellUi();
       } catch (IOException e) {
           return e.getMessage();
       }
    }
}
