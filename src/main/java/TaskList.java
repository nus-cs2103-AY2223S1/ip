import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskArray;

    /**
     * Constructor for generating a new blank TaskList.
     */
    public TaskList() {
        this.taskArray = new ArrayList<>();
    }

    /**
     * Constructor for generating a TaskList from saved file.
     * @param taskArray An ArrayList of tasks to be loaded.
     */
    public TaskList(ArrayList<Task> taskArray) {
        this.taskArray = taskArray;
    }

    /**
     * Add a task to TaskList.
     * @param task Task to add.
     */
    public void addTask(Task task) {
        taskArray.add(task);
    }

    /**
     * Remove a Task.
     * @param taskToRemoveIndex Index of the Task to be removed.
     */
    public void removeTask(int taskToRemoveIndex) {
        taskArray.remove(taskToRemoveIndex);
    }

    /**
     * Getter to retrieve the tasksArray in TaskList.
     * @return ArrayList of Tasks in TaskList.
     */
    public ArrayList<Task> getTasks() {
        return this.taskArray;
    }

    /**
     * Get number of tasks in taskArray.
     * @return Number of tasks in taskArray.
     */
    public int getCount() {
        return getTasks().size();
    }

}
