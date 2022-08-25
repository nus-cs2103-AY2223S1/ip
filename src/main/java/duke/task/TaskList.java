package duke.task;

import java.util.ArrayList;

/**
 * Insert Javadocs
 */
public class TaskList {
    private ArrayList<Task> taskList;


    public TaskList(ArrayList<Task> taskInStorage) {
        this.taskList = taskInStorage;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void delete(int taskNumber) {
        this.taskList.remove(taskNumber);
    }

    public int size() {
        return this.taskList.size();
    }

    public Task getTask(int taskIndex) {
        return this.taskList.get(taskIndex);
    }

    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }
}
