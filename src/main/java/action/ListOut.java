package action;

import task.TaskList;

public class ListOut {

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
