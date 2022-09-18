package commands;

import output.OutputLogger;
import task.TaskResponse;


/**
 * Helper methods for formatting TaskResponse based on use case (create, delete)
 * Commands decide how to format data received from Model, hence this is in commands package
 */
public class TaskResponseFormatter {
    private static String getForm(int count) {
        return count == 1 ? "task" : "tasks";
    }
    /**
     * Formats a TaskResponse for added task to an appropriate String message
     * @param res TaskResponse from TaskModel for an added task
     * @return String representing response for an added task
     */
    public static String addedTask(TaskResponse res) {
        StringBuilder sb = new StringBuilder();
        sb.append("Sure! I've added this task:\n");
        sb.append(OutputLogger.indent(res.getTask().toString()));
        sb.append("\n");
        sb.append(String.format("You now have %d %s to do.", res.getTaskCount(), getForm(res.getTaskCount())));
        return sb.toString();
    }

    /**
     * Formats a TaskResponse for deleted task to an appropriate String message
     * @param res TaskResponse from TaskModel for a deleted task
     * @return String representing response for a deleted task
     */
    public static String deletedTask(TaskResponse res) {
        StringBuilder sb = new StringBuilder();
        sb.append("Noted, I've removed this task:\n");
        sb.append(OutputLogger.indent(res.getTask().toString()));
        sb.append("\n");
        sb.append(String.format("You now have %d %s to do.", res.getTaskCount(), getForm(res.getTaskCount())));
        return sb.toString();
    }
}
