package duke.models;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * The TaskList class is a wrapper around a List of Task objects.
 *
 * @author Zhu Yuanxi
 */
public class TaskList {
    public List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(List<Task> tl) {
        taskList = tl;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task deleteTask(int taskIndex) {
        return taskList.remove(taskIndex);
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int i) {
        return taskList.get(i);
    }

    public void forEach(Consumer<Task> action) {
        for(Task task : taskList) {
            action.accept(task);
        }
    }
    public TaskList findTasks(String pattern) {
        TaskList foundTasks = new TaskList();
        this.taskList.forEach(task -> {
            System.out.println(task.content);
            System.out.println(pattern);
            if (task.content.contains(pattern)) {
                foundTasks.AddTask(task);
            }
        });
        return foundTasks;
    }

    @Override
    public String toString() {
        if (taskList.size() == 0) {
            return "No tasks found!\n";
        }

        StringBuilder fullList = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            fullList.append(i + 1).append(". ").append(taskList.get(i));
        }
        return fullList.toString();
    }
}
