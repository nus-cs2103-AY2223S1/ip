package duke.utils;

import duke.task.Task;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void delete(int num) {
        this.tasks.remove(num);
    }

    public Task getTask(int num) {
        return this.tasks.get(num);
    }

    public void read() {
        for(int i = 0; i < size(); i++) {
            System.out.println(i + 1 + ". " + getTask(i));
        }
    }

    public int size() {
        return this.tasks.size();
    }

    public List<Task> list() {
        return this.tasks;
    }
}
