import java.util.ArrayList;
import java.util.List;

/**
 * Utility class to manage the list of tasks and provides augmenting operations to
 * add to the list of tasks or to enumerate through the list of tasks
 *
 * @author Emily Ong Hui Qi
 */
public class TaskManager {
    // The list of tasks, accessed using 0-based indices
    private final List<Task> taskList;
    private static final String NO_TASKS_AVAILABLE = "There are currently no tasks available. Add one now!";

    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds the received task into the task list
     *
     * @param taskDescription Task description received from the caller
     */
    public void add(String taskDescription) {
        // TODO: Allow creation of various task types
        this.taskList.add(new ToDo(taskDescription));
    }

    /**
     * Retrieve the task
     *
     * @param taskNumber An index (1-index) corresponding to a particular task
     * @return Task corresponding to the particular task index
     */
    public Task get(int taskNumber) throws IndexOutOfBoundsException {
        if (!this.isValidTask(taskNumber)) {
            throw new IndexOutOfBoundsException();
        }
        int taskIndex = this.getTaskIndexFromTaskNumber(taskNumber);
        return this.taskList.get(taskIndex);
    }

    /**
     * Utility method to check if the provided 1-based task number is valid, i.e. there exists a
     * task corresponding to the specified task number
     *
     * @param taskNumber The 1-based task number, possibly corresponding to a particular task
     * @return true if the task index corresponds to a valid task, and false otherwise
     */
    private boolean isValidTask(int taskNumber) {
        int taskIndex = this.getTaskIndexFromTaskNumber(taskNumber);
        return taskIndex >= 0 && taskIndex < this.taskList.size();
    }

    /**
     * Utility method to retrieve the task index from the task number by applying a transformation
     * operation to deduct 1 from the task number to convert it to a 0-based index
     *
     * @param taskNumber The provided task number in 1-based index
     * @return The corresponding task index in 0-based index
     */
    private int getTaskIndexFromTaskNumber(int taskNumber) {
        return taskNumber - 1;
    }

    @Override
    public String toString() {
        if (this.taskList.size() == 0) {
            return TaskManager.NO_TASKS_AVAILABLE;
        }
        StringBuilder taskManagerDisplay = new StringBuilder();
        for (int i = 0; i < this.taskList.size(); i++) {
            // Implicitly invoke the display of the task defined in the Task class
            taskManagerDisplay.append(String.format("%d. %s\n", i + 1, this.taskList.get(i)));
        }
        return taskManagerDisplay.toString();
    }
}
