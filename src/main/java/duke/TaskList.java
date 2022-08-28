package duke;

import java.util.ArrayList;

/**
 * TaskList class uses an ArrayList to represent the list of tasks stored.
 *
 * @author Sheryl Kong (A0240686Y)
 */

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void delete(int taskNo) {
        tasks.remove(tasks.get(taskNo));
    }


}
