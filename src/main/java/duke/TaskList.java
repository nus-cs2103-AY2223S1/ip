package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void removeTask(int index) {
        this.tasks.remove(index);
    }

    public void markTask(int index) {
        this.tasks.get(index).markAsDone();
    }

    public void unmarkTask(int index) {
        this.tasks.get(index).markAsUndone();
    }
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public String formatTasks() {
        String str = "";
        for(int i = 0; i < getSize(); i++) {
            str += getTask(i).formatToSave() + "\n";
        }
        return str;
    }

    @Override
    public String toString() {
        String str = "";
        for(int i = 1; i <= this.getSize(); i++) {
            Task task = this.getTask(i-1);
            str += i + "." + task.toString() + "\n";
        }
        return str;
    }
}
