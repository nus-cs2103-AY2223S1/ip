package duke;

import java.util.List;

/**
 * Represents the user's task list
 *
 * @author  Gerald Teo Jin Wei
 * @version 0.1
 * @since   2022-08-28
 */
public class TaskList {

    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Prints out all the tasks in the user's task list.
     */
    public String printList() {
        String str = "Here are the tasks in your list:\n";
        for (int i = 1; i <= taskList.size(); i++) {
            str += i + "." + taskList.get(i - 1) + "\n";
        }
        return str;
    }

    /**
     * Returns the number of tasks in the user's task list
     * @return int This returns the number of tasks
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Adds a task to the user's task list
     * @param task Task to be added to user's task list
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a task from
     * the user's task list
     * @param task Task to be deleted from user's task list
     */
    public void deleteTask(Task task) {
        taskList.remove(task);
    }

    /**
     * Gets the list of tasks from the user
     * @return List of tasks from the user
     */
    public List<Task> getList() {
        return this.taskList;
    }

    /**
     * Gets the task of a certain number from the list
     * @param i This is the task number
     * @return Task This returns the task of a certain number from the list
     */
    public Task getTask(int i) {
        return this.taskList.get(i);
    }


}
