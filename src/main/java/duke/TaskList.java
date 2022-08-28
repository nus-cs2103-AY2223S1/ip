package duke;

import java.util.ArrayList;

import duke.tasks.Task;

/**
 * Stores lists of tasks and performs actions on them
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list
     * @param task current task list
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the task list
     * @param index index of task to be deleted from current task list
     */
    public void delete(int index) {
        this.tasks.remove(index);
    }

    /**
     * Returns list of all current tasks
     * @return ArrayList of all tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Prints list of all current tasks
     */
    public String getTaskList() {
        StringBuilder taskList = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            taskList.append(i + 1).append(".").append(this.tasks.get(i)).append("\n");
        }
        return taskList.toString();
    }

    /**
     * Marks a specified task on the task list
     * @param index index of task to be marked
     */
    public void markIndex(int index) {
        this.tasks.get(index).mark();
    }

    /**
     * Unmarks a specified task on the task list
     * @param index index of task to be unmarked
     */
    public void unmarkIndex(int index) {
        this.tasks.get(index).unmark();
    }

    /**
     * Prints a specified task on the task list
     * @param index index of task to be printed
     */
    public String getByIndex(int index) {
        return tasks.get(index).toString();
    }

    /**
     * Gets current number of tasks on the task list
     * @return current number of tasks on task list
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Finds all tasks in the current task list that has a description containing keyword
     * @param keyword keyword that should be searched for in tasks
     * @return TaskList task list of tasks that contain the keyword
     */
    public TaskList find(String keyword) {
        TaskList foundTasks = new TaskList(new ArrayList<Task>());
        for (Task task : this.tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

}
