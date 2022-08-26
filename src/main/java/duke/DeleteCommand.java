package duke;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteCommand extends Command {
    @Override
     void execute(String taskName, ArrayList<Task> listOfTasks, Ui ui, Storage storage) throws IOException {
        TaskList taskList = new TaskList(listOfTasks);
        taskList.delete(taskName);
    }
}
