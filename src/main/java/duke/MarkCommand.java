package duke;

import java.io.IOException;
import java.util.ArrayList;

public class MarkCommand extends Command{
    @Override
    void execute(String taskName, ArrayList<Task> listOfTask, Ui ui, Storage storage) throws IOException {
        int i = Integer.parseInt(taskName.substring(5)) - 1;
        Task task = listOfTask.get(i);
        task.mark();
    }
}
