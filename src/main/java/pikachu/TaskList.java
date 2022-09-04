package pikachu;

import pikachu.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the task list to store all tasks. A <code>TaskList</code> object corresponds to
 * a string arraylist containing all tasks
 */
public class TaskList {
    public List<Task> taskList;
    
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
