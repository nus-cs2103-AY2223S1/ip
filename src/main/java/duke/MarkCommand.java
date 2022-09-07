package duke;

import java.io.IOException;
import java.util.ArrayList;

public class MarkCommand extends Command{
    void handleException(int index, ArrayList<Task> listOfTask) throws DukeIndexTooLargeException, DukeNonPositiveIndexException {
        if (index > listOfTask.size() - 1) {
            throw new DukeIndexTooLargeException();
        }
        if (index <= 0) {
            throw new DukeNonPositiveIndexException();
        }
    }
    @Override
    String execute(String taskName, ArrayList<Task> listOfTask, Ui ui, Storage storage) throws IOException {
        try {
            int i = Integer.parseInt(taskName.substring(5)) - 1;
            handleException(i, listOfTask);
            Task task = listOfTask.get(i);
            assert task != null;
            return task.mark();
        } catch (DukeException e) {
           return e.getMessage();
        }
    }
}
