/**
 * Project done by Hong Jin.
 */

import java.util.*;

/**
 * Class Task to store Task.
 */
public class Task {
    private String task;
    private String done;
    private String taskType;

    public Task(String task, String taskType) {
        this.task = task;
        this.done = "[ ]";
        this.taskType = taskType;
    }

    public String getTask() {
        String header = this.taskType + this.done + " ";
        if (taskType == "[T]") {
            return header + this.task;
        } else if (taskType == "[D]") {
            String[] str = task.split("/by", 2);
            return header + str[0] + "(by:" + str[1] + ")";
        } else {
            String[] str = task.split("/at", 2);
            return header + str[0] + "(at:" + str[1] + ")";
        }
    }

    public void mark() {
        this.done = "[X]";
    }

    public void unmark() {
        this.done = "[ ]";
    }
}
