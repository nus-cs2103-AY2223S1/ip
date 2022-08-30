package duke;

import java.io.IOException;
import java.util.ArrayList;

public class UnmarkCommand extends Command{
    String execute(String taskName, ArrayList<Task> listOfTask, Ui ui, Storage storage) throws IOException {
        int i = Integer.parseInt(taskName.substring(7)) - 1;
        Task task = listOfTask.get(i);
        return task.unmark();
    }
}
