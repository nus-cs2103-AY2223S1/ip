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
    public boolean isEmpty() {
        return tasks.size() == 0;
    }

    public Task markTask(Integer index) {
        index--;
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    public Task unmarkTask(Integer index) {
        index--;
        Task task = tasks.get(index);
        task.unMark();
        return task;
    }

    public Task deleteTask(int index) {
        index--;
        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public TaskList find(String stringToFind) {
        TaskList res = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            if (curr.toString().toUpperCase().contains(stringToFind.toUpperCase())) {
                res.addTask(curr);
            }
        }
        return res;
    }

}
