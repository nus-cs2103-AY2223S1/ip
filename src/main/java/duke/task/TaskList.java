package duke.task;

import java.util.ArrayList;

/**
 * Part of the chatbot that perform operations that deal with tasks.
 */
public class TaskList {

    private ArrayList<Task> listOfTasks;

    /**
     * Constructor for the Tasklist object.
     */
    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    /**
     * Return the task at the specific position of the task list.
     * @param taskNum The index of the task to return.
     * @return The task at the specific position of the task list.
     */
    public Task getTask(int taskNum) {
        return listOfTasks.get(taskNum);
    }

    /**
     * Return the list of tasks.
     */
    public ArrayList<Task> getListOfTasks() {
        return this.listOfTasks;
    }

    /**
     * Appends the specific tasks to the end of the task list.
     * @param task The task to be appended to the end of the task list.
     */
    public void addTask(Task task) {
        listOfTasks.add(task);
    }

    /**
     * Return the number of tasks in the task list.
     * @return The number of tasks in the task list.
     */
    public int getTaskSize() {
        return this.listOfTasks.size();
    }

    /**
     * Mark the specific task as done in the task list.
     * @param taskNum The index of the task to mark as done.
     */
    public void markTask(int taskNum) {
        Task currTask = listOfTasks.get(taskNum);
        currTask.markAsDone();
    }

    /**
     * Mark the specific task as not done in the task list.
     * @param taskNum The index of the task to mark as not done.
     */
    public void unmarkTask(int taskNum) {
        Task currTask = listOfTasks.get(taskNum);
        currTask.markAsNotDone();
    }

    /**
     * Delete the specific task in the task list.
     * @param taskNum The index of the task to delete.
     */
    public void deleteTask(int taskNum) {
        listOfTasks.remove(taskNum);
    }

    /**
     * Returns an arraylist containing all the tasks that matches the keyword by the user input.
     * @param keyword The keyword used to find list of tasks that matches it.
     * @return Returns an arraylist containing all the tasks that matches the keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> taskContainingKeyword = new ArrayList<>();
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task currTask = listOfTasks.get(i);
            if (currTask.containKeyword(keyword)) {
                taskContainingKeyword.add(currTask);
            }
        }
        return taskContainingKeyword;
    }
}
