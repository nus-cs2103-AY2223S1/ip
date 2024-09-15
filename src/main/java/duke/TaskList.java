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
     * @return  tasks in the user's task list
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
     * @return number of tasks in user's task list
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Adds a task to the user's task list
     * @param task task to be added to user's task list
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a task from
     * the user's task list
     * @param task task to be deleted from user's task list
     */
    public void deleteTask(Task task) {
        taskList.remove(task);
    }

    /**
     * Returns the list of tasks from the user
     * @return list of tasks from the user
     */
    public List<Task> getList() {
        return this.taskList;
    }

    /**
     * Returns the task of a certain number from the list
     * @param i task number
     * @return task of a certain number from the list
     */
    public Task getTask(int i) {
        return this.taskList.get(i);
    }


}
