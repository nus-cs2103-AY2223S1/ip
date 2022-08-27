package kirby;

import kirby.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;

/**
 * TaskList class handles the list of tasks - adding and deleting tasks from it and other operations.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int taskCount = 0;

    /**
     * Constructor of the TaskList class.
     *
     * @param tasks the list of initial tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        if (tasks == null) {
            this.tasks = new ArrayList<>();
        } else {
            this.tasks = tasks;
            taskCount = tasks.size();
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the number of tasks in the list.
     */
    public int getTaskCount() {
        return this.taskCount;
    }

    /**
     * Sets a specified task as completed.
     *
     * @param taskNumber index of the task to be marked.
     */
    public void setTaskMarked(int taskNumber) {
        tasks.get(taskNumber).setCompleted();
        System.out.println("Awesome :D I've marked " + tasks.get(taskNumber).toString() + " completed!");
    }

    /**
     * Sets a specified task as completed.
     *
     * @param taskNumber index of the task to be unmarked.
     */
    public void setTaskUnmarked(int taskNumber) {
        tasks.get(taskNumber).setIncomplete();
        System.out.println("Okay, I've marked " + tasks.get(taskNumber).toString() + " pending!");
    }

    /**
     * Returns the list of tasks.
     *
     * @return the list of tasks.
     */
    public ArrayList<Task> getList() {
        return this.tasks;
    }

    /**
     * Adds a new task into the list.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        this.taskCount++;
        System.out.println("Added into your bag of fabulous tasks: " + task.toString());
        printTaskCount();
    }

    /**
     * Removes a task from the list.
     *
     * @param taskIndex the index which the task is to be removed.
     */
    public void removeTask(int taskIndex) {
        this.tasks.remove(taskIndex);
        this.taskCount--;
        System.out.println("Removed from your bag of fabulous tasks: " + tasks.get(taskIndex - 1).toString());
        printTaskCount();
    }


    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> res = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                res.add(task);
            }
        }
        return res;
    }

    /**
     * Prints the number of tasks in the list.
     */
    private void printTaskCount() {
        if (taskCount > 1) {
            System.out.println("Now you have " + taskCount + " tasks in the bag!");
        } else {
            System.out.println("Now you have " + taskCount + " task in the bag!");
        }
    }
}
