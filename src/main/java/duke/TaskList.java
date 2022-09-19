package duke;

import java.util.LinkedList;

public class TaskList extends LinkedList {
    private LinkedList<Task> tasks = new LinkedList<>();

    @Override
    public int size() {
        return tasks.size();
    }

    public boolean add(Task task) {
        return this.tasks.add(task);
    }

    public int indexOf(Task task) {
        return this.tasks.indexOf(task);
    }

    public void listAllTasks() {
        this.tasks.forEach(
                task -> { System.out.println(String.format("%d.%s",
                        tasks.indexOf(task) + 1,
                        task.printTask()));
                });
    }

    @Override
    public Task getLast() {
        return this.tasks.getLast();
    }

    @Override
    public Task get(int index) {
        return this.tasks.get(index);
    }
}
