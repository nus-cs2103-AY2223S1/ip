package commands;

import task.TaskResponse;
import output.OutputLogger;

/**
 * Helper methods for formatting TaskResponse based on use case (create, delete)
 * Commands decide how to format data received from Model, hence this is in commands package
 */
public class TaskResponseFormatter {
    public static String addedTask(TaskResponse res) {
        StringBuilder sb = new StringBuilder();
        sb.append("Sure! I've added this task:\n");
        sb.append(OutputLogger.indent(res.task.toString()));
        sb.append("\n");
        sb.append(String.format("You now have %d tasks to do.", res.taskCount));
        return sb.toString();
    }
}
