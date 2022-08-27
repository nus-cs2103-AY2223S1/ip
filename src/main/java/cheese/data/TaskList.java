package cheese.data;

import java.util.ArrayList;
import cheese.exception.CheeseException;
import cheese.task.Task;

/**
 * Represents a list of tasks that supports adding tasks, deleting tasks, and marking tasks as
 * complete/incomplete.
 */
public class TaskList {
    /** List of tasks. */
    private ArrayList<Task> taskList;

    /**
     * Consructs an instance of <code>TaskList</code>.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Gets task from the list at given index.
     * 
     * @param taskIndex Index of task to retrieve.
     * @return Task specified by given index.
     * @throws CheeseException If index is not in the range of task list.
     */
    public Task getTask(int taskIndex) throws CheeseException {
        validateTaskIndexInRange(taskIndex);
        return taskList.get(taskIndex);
    }

    /**
     * Deletes task from the list at given index.
     * 
     * @param taskIndex Index of task to delete.
     * @return Task specified by given index.
     * @throws CheeseException If index is not in the range of task list.
     */
    public Task delete(int taskIndex) throws CheeseException {
        Task task = getTask(taskIndex);
        taskList.remove(task);
        return task;
    }

    /**
     * Adds task to end of the list.
     * 
     * @param task Task to add.
     * @return Task that is added to the list.
     */
    public Task add(Task task) {
        taskList.add(task);
        return task;
    }

    /**
     * Marks task in the list at given index as complete.
     * 
     * @param taskIndex Index of task to mark as complete
     * @return Task that is marked as complete.
     * @throws CheeseException If index is not in the range of task list.
     */
    public Task markTaskAsDone(int taskIndex) throws CheeseException {
        Task task = getTask(taskIndex);
        task.setDone();
        return task;
    }

    /**
     * Marks task in the list at given index as incomplete.
     * 
     * @param taskIndex Index of task to mark as incomplete
     * @return Task that is marked as incomplete.
     * @throws CheeseException If index is not in the range of task list.
     */
    public Task markTaskAsNotDone(int taskIndex) throws CheeseException {
        Task task = getTask(taskIndex);
        task.setNotDone();
        return task;
    }

    /**
     * Gets size of task list.
     * 
     * @return Size of task list.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Returns String representation of task list to save in file.
     * 
     * @return String representation of task list to save in file.
     */
    public String toFileString() {
        String fileString = "";
        for (Task task : taskList) {
            fileString += task.toFileString();
            fileString += System.lineSeparator();
        }
        return fileString;
    }

    /**
     * Returns String representation of task list.
     * 
     * @return String representation of task list.
     */
    @Override
    public String toString() {
        String taskListString = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            int displayIndex = i + 1;
            taskListString += displayIndex + ". " + task;
            taskListString += System.lineSeparator();
        }
        return taskListString.trim();
    }

    /**
     * Validates if index given is within range of task list.
     * 
     * @param taskIndex Index of task to validate.
     * @throws CheeseException If index is not in the range of task list.
     */
    private void validateTaskIndexInRange(int taskIndex) throws CheeseException {
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new CheeseException("Item number is not in list range.");
        }
    }
}
