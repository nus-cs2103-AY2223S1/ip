package util;

import data.tasks.Task;

public class CommandUtils {

    public static String generateAddTaskResponse(Task task, int currNumTasks) {
        return String.join("\n",
            "Got it. I've added this task:", String.format("\t%s", task),
            String.format("Now you have %d tasks in the list", currNumTasks));
    }

    public static String generateDeleteTaskResponse(Task task, int currNumTasks) {
        return String.join("\n",
            "Noted. I've removed this task:", String.format("\t%s", task),
            String.format("Now you have %d tasks in the list", currNumTasks));
    }
}
