package sky;

import sky.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class contains the task list e.g., it has operations to add/delete tasks in the list.
 */
public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public String printTasks() {
        String s = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            s+= "  " + (i + 1) + "." + this.taskList.get(i) + (i == this.taskList.size() - 1 ? "" : "\n");
        }
        return s;
    }

    public Task getTask(int taskNum) {
        return this.taskList.get(taskNum);
    }

    public int size() {
        return this.taskList.size();
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void removeTask(Task task) {
        this.taskList.remove(task);
    }
}
