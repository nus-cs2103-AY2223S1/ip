package duke;

import java.io.IOException;
import java.util.ArrayList;

public class EventCommand extends Command{

    @Override
    String execute(String taskName, ArrayList<Task> listOfTasks, Ui ui, Storage storage)
            throws IOException, DukeEventEmptyException {
        if(taskName.length() == 5) {
            throw new DukeEventEmptyException();
        }
        int index = taskName.indexOf("/");
        Task t = new Event(taskName.substring(6, index - 1),
                false, taskName.substring(index + 4));
        TaskList taskList = new TaskList(listOfTasks);
        return taskList.addToList(t);
    }

}
