package util;

import data.tasks.Task;

import java.util.Arrays;
import java.util.List;

public class CommandUtils {

    public static List<String> generateAddTaskResponse(Task task, int currNumTasks) {
        return Arrays.asList("Got it. I've added this task:", String.format("\t%s", task),
            String.format("Now you have %d tasks in the list", currNumTasks));
    }
}
