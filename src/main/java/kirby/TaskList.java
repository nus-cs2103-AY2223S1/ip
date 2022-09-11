package kirby;

import static kirby.time.HandleTime.sortTaskList;

import java.util.ArrayList;

import kirby.tasks.Task;

/**
 * TaskList class handles the list of tasks - adding and deleting tasks from it and other operations.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private int taskCount;

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
        assert tasks != null;
        tasks.get(taskNumber).setCompleted();
    }

    public String setMarkedString(int taskNumber) {
        return "Awesome :D I've marked \n" + tasks.get(taskNumber).toString();
    }

    /**
     * Sets a specified task as completed.
     *
     * @param taskNumber Index of the task to be unmarked.
     */
    public void setTaskUnmarked(int taskNumber) {
        assert tasks != null;
        tasks.get(taskNumber).setIncomplete();
    }

    public String setUnmarkedString(int taskNumber) {
        return "Okay, I've unmarked \n" + tasks.get(taskNumber).toString();
    }

    /**
     * Returns the list of tasks.
     *
     * @return List of tasks sorted in chronological order.
     */
    public ArrayList<Task> getList() {
        if (this.tasks.size() <= 1) {
            return this.tasks;
        }
        return sortTaskList(this.tasks);
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

    /**
     * Prints message when a task is added.
     *
     * @param task Task to be added.
     */
    public String addTaskString(Task task) {
        return "Added into bag of fabulous tasks: \n" + task.toString() + "\n" + printTaskCount(0);
    }

    /**
     * Removes a task from the list.
     *
     * @param taskIndex Index which the task is to be removed.
     */
    public void removeTask(int taskIndex) {
        assert tasks != null;
        this.tasks.remove(taskIndex);
        this.taskCount--;
    }

    /**
     * Prints message when a task is removed.
     *
     * @param taskIndex Task index to be removed.
     */
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
        ArrayList<Task> findTaskList = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                findTaskList.add(task);
            }
        }
        return findTaskList;
    }

    /**
     * Prints message when a task is found.
     *
     * @param tasks All the tasks found.
     */
    public String findTaskString(ArrayList<Task> tasks) {
        StringBuilder taskListPara = new StringBuilder();
        for (Task task: tasks) {
            taskListPara.append(task.toString()).append("\n");
        }
        return taskListPara.toString();
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
