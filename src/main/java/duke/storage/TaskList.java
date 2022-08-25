package duke.storage;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;
    private int count = 0;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        count = tasks.size();
    }

    public void iterate() {
        int i = 1;
        for (Task task : tasks) {
            System.out.println(i + "." + task.toString());
            i++;
        }
    }
    public String getCount() {
        return "Now you have " + this.count + " tasks in the list.";
    }

    public Task findTask(int index) {
        return this.tasks.get(index);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void add(Task task) {
        tasks.add(task);
        count++;
    }
    public void delete(Task task) {
        tasks.remove(task);
        count--;
    }

}
