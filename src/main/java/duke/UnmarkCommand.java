package duke;

import java.util.ArrayList;


public class UnmarkCommand extends Command {
    void handleException(int index, ArrayList<Task> listOfTask) throws DukeIndexTooLargeException, DukeNonPositiveIndexException {
        if (index > listOfTask.size() - 1) {
            throw new DukeIndexTooLargeException();
        }
        if (index <= -1) {
            throw new DukeNonPositiveIndexException();
        }
    }
   
    @Override
    String execute(String taskName, ArrayList<Task> listOfTask, Ui ui, Storage storage) {
        try {
        int taskNumber = getTaskNumberOfTaskToBeUnmarked(taskName);
        handleException(taskNumber, listOfTask); 
        Task task = getTaskToBeUnmarked(taskNumber,listOfTask);
        return task.unmark();
        } catch (DukeException e) {
           return e.getMessage(); 
        } 
    }

    int getTaskNumberOfTaskToBeUnmarked(String taskName) {
        return Integer.parseInt(taskName.substring(7)) - 1;
    }

    Task getTaskToBeUnmarked(int taskNumber, ArrayList<Task> listOfTask) {
        return listOfTask.get(taskNumber);
    }


}
