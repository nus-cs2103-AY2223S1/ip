package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents the list of Tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList, setting tasks to a given list.
     * @param list List to be assigned to task.
     */
    public TaskList(ArrayList<Task> list) {
        this.tasks = list;
    }

    /**
     * Adds a new Task to tasks.
     * @param toAdd Task to add.
     */
    public void add(Task toAdd) {
        this.tasks.add(toAdd);
    }

    /**
     * Removes Task from given index from tasks.
     * @param index index of task to remove.
     * @return the removed Tasks.
     */
    public Task remove(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Gets the size of tasks.
     * @return size of tasks.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Converts Task of a given index in tasks into its string representation.
     * @param index index of the Task.
     * @return String representation of Task with given index.
     */
    public String taskToString(int index) {
        return this.tasks.get(index).toString();
    }

    /**
     * Converts Tasks of a given index in tasks to its string representation to save.
     * @param index index of Task.
     * @return String representation of Task with given index.
     */
    public String taskSaveToString(int index) {
        return this.tasks.get(index).storedString();
    }

    /**
     * Marks a Task at given index in tasks as done.
     * @param index index of Task.
     */
    public void markAsDone(int index) {
        this.tasks.get(index).markDone();
    }

    /**
     * Marks a Task at given index in tasks as not done yet.
     * @param index index of Task.
     */
    public void unMarkDone(int index) {
        this.tasks.get(index).unMark();
    }

    /**
     * Find all the tasks with given keyword in TaskList.
     * @param keyword Keyword to find tasks.
     * @return TaskList containing matching tasks.
     */
    public TaskList findTask(String keyword) {
        ArrayList<Task> tasksWithKeyword = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).hasKeyword(keyword)) {
                tasksWithKeyword.add(this.tasks.get(i));
            }
        }
        return new TaskList(tasksWithKeyword);
    }

    @Override
    public String toString() {
        return tasks.toString();
    }
}
