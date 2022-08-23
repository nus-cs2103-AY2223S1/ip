package sky;

import sky.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList class contains the task list e.g., it has operations to add/delete tasks in the list.
 */
public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the tasks stored in the task list in the form of a String and respectively separated
     * by a newline.
     * Returns an empty string if the task list is empty.
     *
     * @return Tasks stored in the task list in the form of a String and respectively separated by a
     * newline.
     */
    public String printTasks() {
        String s = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            s += "  " + (i + 1) + "." + this.taskList.get(i) + (i == this.taskList.size() - 1 ? "" : "\n");
        }
        return s;
    }

    /**
     * Returns a task of the specified index from the task list.
     *
     * @param taskNum Specified index of the task to be retrieved from the task list.
     * @return Task to be retrieved from the task list.
     */
    public Task getTask(int taskNum) {
        return this.taskList.get(taskNum);
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size of the task list.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added into the task list.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param task Task to be removed from the task list.
     */
    public void removeTask(Task task) {
        this.taskList.remove(task);
    }
}
