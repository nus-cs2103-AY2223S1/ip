package duke;

import java.util.ArrayList;

public class UnmarkCommand extends Command {
    String execute(String taskName, ArrayList<Task> listOfTask, Ui ui, Storage storage) {
        int taskNumber = getTaskNumberOfTaskToBeUnmarked(taskName);
        Task task = getTaskToBeUnmarked(taskNumber,listOfTask);
        return task.unmark();
    }

    int getTaskNumberOfTaskToBeUnmarked(String taskName) {
        return Integer.parseInt(taskName.substring(7)) - 1;
    }

    Task getTaskToBeUnmarked(int taskNumber, ArrayList<Task> listOfTask) {
        return listOfTask.get(taskNumber);
    }

}
