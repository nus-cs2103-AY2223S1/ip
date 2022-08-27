package maria;

import java.util.List;

import maria.task.Task;
import maria.task.TaskList;
import maria.util.StorageConverter;

/**
 * Acts as the interface for all the tasks related logic in the program.
 */
public class TaskManager {

    private final TaskList taskList;

    private final Storage storage;

    private List<Task> taskListFindResult;

    /**
     * Starts the task manager by reading from the storage.
     */
    public TaskManager() {

        this.storage = new Storage("tasks.mariadata");
        this.taskList = new TaskList(this.storage);
        StorageConverter.stringToTasks(this);

    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setTaskListFindResult(List<Task> taskListFindResult) {
        this.taskListFindResult = taskListFindResult;
    }

    /**
     * Converts the task list to a multiline, human-readable String.
     * @return The task list string
     */
    public String getTaskListFindResultString() {

        if (this.taskListFindResult == null || this.taskListFindResult.size() == 0) {
            return "There are no tasks available.";
        } else {
            // Converts the task list to a multiline string for display
            return this.taskListFindResult.stream()
                    .map(task -> "- " + task.getName() + " " + task)
                    .reduce((acc, str) -> acc + "\n" + str).get();
        }

    }

}
