package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int size;

    public TaskList() {
        this.tasks = new ArrayList<>(100);
        this.size = 0;
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(100);
        this.tasks.addAll(tasks);
        this.size = tasks.size();
    }

    public int getSize() {
        return this.size;
    }

    public void add(Task task) {
        this.tasks.add(this.tasks.size(), task);
        this.size++;
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }

    public Task delete(int i) {
        this.size--;
        return this.tasks.remove(i);
    }

}
