package action;

import java.util.ArrayList;

import task.Task;
import task.TaskList;


/**\
 * Find is a class that searches for tasks that contain a specified word.
 */
public class Find {

    /**
     * FindTasks searches for the tasks that have a specified word and returns that list of Tasks.
     * @param str The array of words that contains the specified word.
     * @param taskList The TaskList to search the task from.
     * @return The list of Tasks that contain the specified word.
     */
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
        if (output.toString().isBlank()) {
            return "----------------------\n" + "Oops nothing fits the description :(\n";
        } else {
            return "----------------------\n" + output + "----------------------\n";
        }
    }
}
