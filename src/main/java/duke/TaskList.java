package duke;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public void markTaskAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    public void markTaskAsUndone(int index) {
        tasks.get(index).markAsUndone();
    }

    public void delete(int index) {
        tasks.remove(index);
    }

    public void list() {
        Task[] x = new Task[tasks.size()];
        Task[] tasksArray = tasks.toArray(x);
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= tasksArray.length; i++) {
            Task task = tasksArray[i - 1];
            System.out.println(i + "." + task.toString());
        }
    }
}
