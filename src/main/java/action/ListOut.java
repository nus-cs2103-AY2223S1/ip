package action;

import task.TaskList;

public class ListOut {

    public static String list(String[] str, TaskList taskList) {
        if (str.length == 1) {
            String out = "";
            int currentAction = taskList.taskListSize();
            for (int i = 0; i < currentAction; i++) {
                out = out + String.format("%d", i + 1) + "." + taskList.getTaskList().get(i) + "\n";
            }
            out = "----------------------\n" + out + "----------------------\n";
            return out;
        } else {
            return "Error";
        }
    }
}
