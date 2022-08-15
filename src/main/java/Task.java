/**
 * Project done by Hong Jin.
 */

import java.util.*;

/**
 * Class Task to store Task.
 */
public class Task {
    private String task;
    private boolean done;
    private String taskType;

    public Task(String task, String taskType) {
        this.task = task;
        this.done = false;
        this.taskType = taskType;
    }

    public String getTask() {
        return this.task;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public String getTaskList() {
        if (!this.done) {
            return "[" + this.taskType + "]" + "[ ] " + this.task;
        } else {
            return "[" + this.taskType + "]" +"[X] " + this.task;
        }
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }
}
