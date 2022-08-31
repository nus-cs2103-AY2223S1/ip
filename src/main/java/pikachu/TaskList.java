package pikachu;

import pikachu.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public List<Task> taskList;
    
    TaskList() {
        taskList = new ArrayList<>();
    }

    TaskList(List<Task> existTasks) {
            taskList = existTasks;
    }
}
