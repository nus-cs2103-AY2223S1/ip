package duke;

import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void delete(int index) {
        this.tasks.remove(index);
    }
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void printTaskList() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println(i + 1 + "." + this.tasks.get(i));
        }
    }

    public void markIndex(int index) {
        this.tasks.get(index).mark();
    }

    public void unmarkIndex(int index) {
        this.tasks.get(index).unmark();
    }

    public void printByIndex(int index) {
        System.out.println(tasks.get(index));
    }

    public int getSize() {
        return this.tasks.size();
    }
}
