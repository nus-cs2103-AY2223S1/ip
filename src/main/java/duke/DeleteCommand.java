package duke;

import java.io.IOException;
import java.util.ArrayList;

public class DeleteCommand extends Command {
    void handleException(int index, ArrayList<Task> listOfTask) throws DukeIndexTooLargeException, DukeNonPositiveIndexException {
        if (index > listOfTask.size() - 1) {
            throw new DukeIndexTooLargeException();
        }
        if (index <= 0) {
            throw new DukeNonPositiveIndexException();
        }
    }
    @Override
   String execute(String fullCommand, ArrayList<Task> listOfTasks, Ui ui, Storage storage) throws IOException {
        try {
            TaskList taskList = new TaskList(listOfTasks);
            int index = Integer.parseInt(fullCommand.substring(7));
            handleException(index, listOfTasks);
            return taskList.delete(fullCommand);
        } catch (DukeException e) {
           return e.getMessage();
        }
   }
}
