package duke.data;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Stores the list of tasks for the program.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public int size() {
        return this.taskList.size();
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void remove(int index) {
        this.taskList.remove(index);
    }

    public ArrayList<Task> getAllTasks() {
        return this.taskList;
    }
}
