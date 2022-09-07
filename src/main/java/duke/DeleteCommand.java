package duke;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    void handleException(int index, ArrayList<Task> listOfTask) throws DukeIndexTooLargeException, DukeNonPositiveIndexException {
        if (index > listOfTask.size() - 1) {
            throw new DukeIndexTooLargeException();
        }
        if (index <= -1) {
            throw new DukeNonPositiveIndexException();
        }
    }
    @Override
   String execute(String fullCommand, ArrayList<Task> listOfTasks, Ui ui, Storage storage) {
        try {
            TaskList taskList = new TaskList(listOfTasks);
            int index = Integer.parseInt(fullCommand.substring(7)) - 1;
            handleException(index, listOfTasks);
            return taskList.delete(fullCommand);
        } catch (DukeException e) {
           return e.getMessage();
        }
   }
}
