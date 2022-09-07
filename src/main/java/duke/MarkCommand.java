package duke;

import java.io.IOException;
import java.util.ArrayList;

public class MarkCommand extends Command {
    @Override
    String execute(String taskName, ArrayList<Task> listOfTask, Ui ui, Storage storage) {
        int taskNumber = getTaskNumberOfTaskToBeMarked(taskName);
        Task task = getTaskToBeMarked(taskNumber, listOfTask);
        return task.mark();
    }

    int getTaskNumberOfTaskToBeMarked(String taskName) {
       return Integer.parseInt(taskName.substring(5)) - 1;
    }

    Task getTaskToBeMarked(int taskNumber, ArrayList<Task> listOfTask) {
        return listOfTask.get(taskNumber);
    }
}