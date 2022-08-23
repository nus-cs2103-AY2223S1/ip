package util;

import command.CommandResponse;
import data.tasks.Task;

import java.util.Arrays;

public class CommandUtils {

    public static CommandResponse generateAddTaskResponse(Task task, int currNumTasks) {
        return new CommandResponse(
            Arrays.asList("Got it. I've added this task:", String.format("\t%s", task),
                String.format("Now you have %d tasks in the list", currNumTasks)), true);
    }

    public static CommandResponse generateDeleteTaskResponse(Task task, int currNumTasks) {
        return new CommandResponse(
            Arrays.asList("Noted. I've removed this task:", String.format("\t%s", task),
                String.format("Now you have %d tasks in the list", currNumTasks)), true);
    }
}
