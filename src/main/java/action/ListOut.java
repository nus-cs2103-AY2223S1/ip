package action;

import task.TaskList;

/**
 * ListOut is a class that lists out all the tasks in a TaskList.
 */
public class ListOut {

    /**
     * List lists out all the Tasks in a TaskList.
     * @param str The user input that contains the command to List.
     * @param taskList The TaskList to List the task from.
     * @return The string of list of all the Tasks.
     */
    public static String list(String[] str, TaskList taskList) {
        if (str.length == 1) {
            StringBuilder out = new StringBuilder();
            int currentAction = taskList.taskListSize();
            for (int i = 0; i < currentAction; i++) {
                out.append(String.format("%d", i + 1)).append(".").append(taskList.getTaskList().get(i)).append("\n");
            }
            out = new StringBuilder("----------------------\n" + out + "----------------------\n");
            return out.toString();
        } else {
            return "Error";
        }
    }
}
