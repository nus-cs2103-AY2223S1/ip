package pikachu;

import java.util.ArrayList;
import java.util.List;

import pikachu.task.Task;

/**
 * Represents the task list to store all tasks. A <code>TaskList</code> object corresponds to
 * a string arraylist containing all tasks.
 */
public class TaskList {
    private List<Task> taskList;
    TaskList() {
        taskList = new ArrayList<>();
    }

    TaskList(List<Task> existTasks) {
        taskList = existTasks;
    }

    public List<Task> getTaskList() {
        return taskList;
    }
}
