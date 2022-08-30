package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.Storage;
import duke.exception.DukeException;



/**
 * Represent a list containing the tasks.
 */
public class TasksList {
    private List<Task> listOfTasks;
    private final Storage storage;

    /**
     * Creates a new TasksList instance with the corresponding Storage file and list of saved tasks.
     * @param path A pathname string.
     */
    public TasksList(String path) {
        this.storage = new Storage(path);
        this.listOfTasks = this.getSavedTasks();
    }

    /**
     * Retrieve the tasks saved in the storage file.
     * @return A list of saved Tasks.
     */
    private List<Task> getSavedTasks() {
        String storageTasks = this.storage.read();
        return this.parseToTasks(storageTasks);
    }

    /**
     * Parse the string representing the stored tasks into a list of Tasks.
     * @param storageTasks A string representing the stored tasks.
     * @return A list of Tasks.
     */
    private List<Task> parseToTasks(String storageTasks) {
        if (storageTasks == null) {
            return new ArrayList<>();
        }

        String[] arrayOfTaskStrings = storageTasks.split(System.lineSeparator());
        List<Task> taskList = new ArrayList<>();

        for (String taskString: arrayOfTaskStrings) {
            Task task = Task.fromStorageString(taskString);
            taskList.add(task);
        }
        return taskList;
    }

    /**
     * Convert the Tasks into a string representation and saves it to the Storage file.
     */
    public void saveTasks() {
        StringBuilder storageTasks = new StringBuilder();

        for (int i = 0; i < this.listOfTasks.size(); i++) {
            if (i > 0) {
                storageTasks.append(System.lineSeparator());
            }
            Task currentTask = this.listOfTasks.get(i);
            storageTasks.append(currentTask.toStorageString());
        }
        this.storage.write(storageTasks.toString());
    }

    /**
     * Add a task to the TasksList.
     * @param task The Task to be added.
     */
    public void addToList(Task task) {
        this.listOfTasks.add(task);
    }

    /**
     * Mark a Task as completed.
     * @param taskNumber Task number of the task to be marked.
     * @return The Task marked.
     * @throws DukeException If the Task number is invalid.
     */
    public Task markAsDone(int taskNumber) throws DukeException {
        if (taskNumber < 1 || taskNumber > this.listOfTasks.size()) {
            throw new DukeException("Please enter a valid task number!");
        } else {
            Task taskToMark = this.listOfTasks.get(taskNumber - 1);
            taskToMark.markAsDone();
            return taskToMark;
        }
    }
    /**
     * Mark a Task as uncompleted.
     * @param taskNumber Task number of the task to be marked.
     * @return The Task marked.
     * @throws DukeException If the Task number is invalid.
     */
    public Task markAsUndone(int taskNumber) throws DukeException {
        if (taskNumber < 1 || taskNumber > this.listOfTasks.size()) {
            throw new DukeException("Please enter a valid task number!");
        } else {
            Task taskToMark = this.listOfTasks.get(taskNumber - 1);
            taskToMark.markAsUndone();
            return taskToMark;
        }
    }

    /**
     * Remove a Task from the TasksList.
     * @param taskNumber Task number of the task to be removed.
     * @return The Task removed.
     * @throws DukeException If the Task number is invalid.
     */
    public Task deleteTask(int taskNumber) throws DukeException {
        if (taskNumber < 1 || taskNumber > this.listOfTasks.size()) {
            throw new DukeException("Please enter a valid task number!");
        } else {
            Task taskToDelete = this.listOfTasks.get(taskNumber - 1);
            this.listOfTasks.remove(taskToDelete);
            return taskToDelete;
        }
    }

    /**
     * Filters the TasksList for Tasks that matches the keyword.
     * @param keyword The word to be matched with.
     * @return A List containing the matching Tasks.
     */
    public List<Task> findMatchingTasks(String keyword) {
        List<Task> matchedTasks = new ArrayList<>();
        for (Task task: this.listOfTasks) {
            if (task.hasKeyword(keyword)) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }



    /**
     * Returns the string representation of TasksList to be displayed.
     * @return A String representation of TasksList to be displayed.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n\n");
        for (int i = 1; i <= this.listOfTasks.size(); i++) {
            sb.append(String.format("%d. %s\n", i, this.listOfTasks.get(i - 1)));
        }
        return sb.toString();
    }

    /**
     * Returns the number of Tasks in the TasksList.
     * @return Number of Tasks in the TasksList.
     */
    public int getLength() {
        return this.listOfTasks.size();
    }

}
