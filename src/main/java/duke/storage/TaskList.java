package duke.storage;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Acts as the storage of the Tasks of the users.
 * This class contain an ArrayList as attribute which stores the Tasks.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Constructs TaskList object.
     * Creates and assigns an empty ArrayList to lst attribute.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds the Task into the attribute of TaskList.
     *
     * @param task The task to be added as per request by user's input.
     */
    public void addTask(Task task) {
        if (!this.taskList.contains(task)) {
            this.taskList.add(task);
        } else {
            this.taskList.set(taskList.indexOf(task), task);
        }
    }

    /**
     * Deletes the Task into the attribute of TaskList.
     *
     * @param idx The index position of to-be-deleted task in ArrayList.
     * @return The deleted tasks.
     */
    public Task delete(int idx) {
        Task currTask = this.taskList.get(idx);
        this.taskList.remove(idx);
        return currTask;
    }

    public Task get(int idx) {
        return this.taskList.get(idx);
    }

    public ArrayList<Task> getList() {
        return this.taskList;
    }
}
