package action;

import task.Task;
import task.TaskList;

import java.util.ArrayList;

public class Find {

    public static String findTasks(String[] str, TaskList taskList) {
        String search = str[1];
        String output = "";
        int counter = 1;
        ArrayList<Task> listOfTasks = taskList.getTaskList();
        for (int i = 0; i < taskList.taskListSize(); i++) {
            if (listOfTasks.get(i).hasWord(search)) {
                output = output + String.format("%d. ", counter) + listOfTasks.get(i) + "\n";
                counter++;
            }
        }

        if (output.isBlank()) {
            String out = "----------------------\n" + "Oops nothing fits the description :(\n"
                    + "----------------------\n";
            return out;
        } else {
            String out = "----------------------\n" +
                    output + "----------------------\n";
            return out;
        }
    }
}
