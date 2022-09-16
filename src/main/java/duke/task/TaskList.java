package duke.task;

import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList which stores the users task in an ArrayList.
 *
 * @author Leong Jia Hao Daniel
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Constructs the TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Constructs the taskList when the input file is
     * not empty.
     *
     * @param list The arraylist that stores the tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.taskList = list;
    }

    /**
     * Returns the ArrayList that stores the task.
     *
     * @return The ArrayList that stores the task.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Returns the number of tasks left.
     *
     * @return The number of tasks left.
     */
    public int numberOfTasks() {
        return taskList.size();
    }

    /**
     * Returns a String saying how many tasks the user has left.
     *
     * @return The string representation of tasks left.
     */
    public String tasksLeft() {
        return "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Adds a task to the array list.
     *
     * @param task The task that is being added to the list.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task to be removed.
     */
    public void deleteTask(int index) {
        taskList.remove(index);
    }

    /**
     * Returns the task at the particular index.
     *
     * @param index The index of the task.
     * @return Return the Task.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    public ArrayList<Integer> getIndexOfTasks(Task taskType) {
        ArrayList<Integer> tasks = new ArrayList<>();
        if (taskType == null) {
            for (int i = 0; i < taskList.size(); i++) {
                tasks.add(i);
            }
            return tasks;
        }
        for (int i = 0; i < taskList.size(); i++) {
            Task task = getTask(i);
            if (task.getClass().equals(taskType.getClass())) {
                tasks.add(i);
            }
        }
        return tasks;
    }

    /**
     * Clears all the completed tasks.
     *
     * @return The string that writes all the tasks.
     */
    public String clearCompletedTasks() {
        List<Task> tasks = new ArrayList<>();
        String displayTasks = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            if (currentTask.getStatusIcon().equals("X")) {
                tasks.add(currentTask);
                displayTasks += currentTask + "\n";
            }
        }
        taskList.removeAll(tasks);
        return displayTasks;
    }

}
