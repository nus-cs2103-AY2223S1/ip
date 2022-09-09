package action;

import task.Task;
import task.TaskList;

import java.util.ArrayList;

public class Find {

    public static String findTasks(String[] str, TaskList taskList) {
        String search = str[1];
        StringBuilder output = new StringBuilder();
        int counter = 1;
        ArrayList<Task> listOfTasks = taskList.getTaskList();
        for (int i = 0; i < taskList.taskListSize(); i++) {
            if (listOfTasks.get(i).hasWord(search)) {
                output.append(String.format("%d. ", counter)).append(listOfTasks.get(i)).append("\n");
                counter++;
            }
        }


        if (output.isBlank()) {
            return "----------------------\n" + "Oops nothing fits the description :(\n"

        if (output.toString().isBlank()) {

                    + "----------------------\n";
        } else {
            return "----------------------\n" +
                    output + "----------------------\n";
        }
    }
}
