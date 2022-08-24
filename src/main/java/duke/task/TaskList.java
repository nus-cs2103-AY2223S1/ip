package duke.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        tasks = taskList;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Integer getNumOfRemainingTasks() {
        return tasks.size();
    }

    public Task markTask(Integer index) {
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    public Task unmarkTask(Integer index) {
        Task task = tasks.get(index);
        task.unMark();
        return task;
    }

    public Task deleteTask(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

}
