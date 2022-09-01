package kirby;

import java.util.ArrayList;

import kirby.tasks.Task;

/**
 * TaskList class handles the list of tasks - adding and deleting tasks from it and other operations.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int taskCount = 0;

    /**
     * Constructor of the TaskList class.
     *
     * @param tasks List of initial tasks.
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
     * @return Number of tasks in the list.
     */
    public int getTaskCount() {
        return this.taskCount;
    }

    /**
     * Sets a specified task as completed.
     *
     * @param taskNumber Index of the task to be marked.
     */
    public void setTaskMarked(int taskNumber) {
        tasks.get(taskNumber).setCompleted();
    }

    public String setMarkedString(int taskNumber) {
        return "Awesome :D I've marked \n" + tasks.get(taskNumber).toString() + " completed!";
    }

    /**
     * Sets a specified task as completed.
     *
     * @param taskNumber Index of the task to be unmarked.
     */
    public void setTaskUnmarked(int taskNumber) {
        tasks.get(taskNumber).setIncomplete();
    }

    public String setUnmarkedString(int taskNumber) {
        return "Okay, I've marked \n" + tasks.get(taskNumber).toString() + " pending!";
    }

    /**
     * Returns the list of tasks.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getList() {
        return this.tasks;
    }

    /**
     * Adds a new task into the list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        this.taskCount++;
    }

    public String addTaskString(Task task) {
        return "Added into bag of fabulous tasks: \n" + task.toString() + "\n" + printTaskCount(0);
    }

    /**
     * Removes a task from the list.
     *
     * @param taskIndex Index which the task is to be removed.
     */
    public void removeTask(int taskIndex) {
        this.tasks.remove(taskIndex);
        this.taskCount--;
    }

    public String removeTaskString(int taskIndex) {
        return "Removed from bag of fabulous tasks: \n" + tasks.get(taskIndex).toString() + "\n" + printTaskCount(1);
    }
    /**
     * Removes a task from the list.
     *
     * @param keyword Word to be searched in the list of tasks.
     * @return List of tasks where each task contains the keyword.
     */
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
     *
     * @param x 1 if a removeTask function is calling this function. Otherwise. it is 0.
     */
    private String printTaskCount(int x) {
        if (x == 0) {
            if (taskCount > 1) {
                return "Now you have " + taskCount + " tasks in the bag!";
            } else {
                return "Now you have " + taskCount + " task in the bag!";
            }
        } else {
            int newTaskCount = taskCount - 1;
            if (newTaskCount > 1) {
                return "Now you have " + newTaskCount + " tasks in the bag!";
            } else {
                return "Now you have " + newTaskCount + " task in the bag!";
            }
        }
    }
}
