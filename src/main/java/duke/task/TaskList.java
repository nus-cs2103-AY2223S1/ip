package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.Storage;
import duke.exception.DukeException;

/**
 * TaskList represents the list of tasks.
 */
public class TaskList {
    private List<Task> taskList;
    private final Storage storage;

    /**
     * Creates a TaskList with the corresponding Storage file and list of saved tasks.
     *
     * @param path The path of the Storage file.
     */
    public TaskList(String path) {
        this.storage = new Storage(path);
        this.taskList = getSavedTasks();
    }
    /**
     * Returns a List of the Tasks saved.
     *
     * @return List of Tasks.
     */
    private List<Task> getSavedTasks() {
        String storageTasks = storage.read();
        return parse(storageTasks);
    }

    /**
     * Parses a String of Tasks into a List of Tasks.
     *
     * @param string The String of Tasks.
     * @return List of Tasks.
     */
    private List<Task> parse(String string) {
        if (string == null) {
            return new ArrayList<>();
        }

        String[] taskStrings = string.split(System.lineSeparator());
        List<Task> taskList = new ArrayList<>();

        for (String taskString : taskStrings) {
            Task task = Task.fromStorageString(taskString);
            taskList.add(task);
        }

        return taskList;
    }

    /**
     * Saves the list of Tasks to the storage file.
     */
    public void saveTasks() {
        StringBuilder storageTasks = new StringBuilder();

        for (int i = 0; i < taskList.size(); i++) {
            if (i > 0) {
                storageTasks.append(System.lineSeparator());
            }
            storageTasks.append(taskList.get(i).toStorageString());
        }

        storage.write(storageTasks.toString());
    }

    /**
     * Adds a new task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes the Task at the given index from the TaskList.
     *
     * @param index The given index.
     * @return The task removed.
     * @throws DukeException If the index is out of range.
     */
    public Task deleteTask(int index) throws DukeException {
        if (index < 0 || index >= getSize()) {
            throw new DukeException("Invalid task number.");
        } else {
            Task task = taskList.get(index);
            taskList.remove(index);
            return task;
        }
    }

    /**
     * Returns the current size of the list.
     *
     * @return Current size of the list.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Marks the Task at the given index as completed.
     *
     * @param index The given index.
     * @return The Task marked.
     * @throws DukeException If the index is out of range.
     */
    public Task markDone(int index) throws DukeException {
        if (index < 0 || index >= getSize()) {
            throw new DukeException("Invalid task number.");
        } else {
            Task task = taskList.get(index);
            task.markDone();
            return task;
        }
    }

    /**
     * Marks the Task at the given index as uncompleted.
     *
     * @param index The given index.
     * @return The Task unmarked.
     * @throws DukeException If the index is out of range.
     */
    public Task unmarkDone(int index) throws DukeException {
        if (index < 0 || index >= getSize()) {
            throw new DukeException("Invalid task number.");
        } else {
            Task task = taskList.get(index);
            task.markUndone();
            return task;
        }
    }

    /**
     * Returns the String representation of the TaskList.
     *
     * @return String representation of the TaskList.
     */
    @Override
    public String toString() {
        String[] stringList = new String[taskList.size()];
        for (int i = 0; i < taskList.size(); i++) {
            stringList[i] = (i + 1) + ". " + taskList.get(i) + "\n";
        }
        return String.join("", stringList);
    }

    /**
     * Returns a List of Tasks that matches the given keywords.
     *
     * @param keywords The keywords to match with.
     * @return The List of matching Tasks.
     */
    public List<Task> findKeywords(String ... keywords) {
        List<Task> matchedTasks = new ArrayList<>();
        for (Task task : taskList) {
            boolean isMatchingKeywords = true;
            for (String keyword : keywords) {
                if (!task.hasKeyword(keyword)) {
                    isMatchingKeywords = false;
                    break;
                }
            }
            if (isMatchingKeywords) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }

    /**
     * Returns a List of Tasks that matches the given tags.
     *
     * @param tags The tags to match with.
     * @return The List of matching Tasks.
     */
    public List<Task> findTags(String ... tags) {
        List<Task> matchedTasks = new ArrayList<>();
        for (Task task : taskList) {
            boolean isMatchingTags = true;
            for (String tag : tags) {
                if (!task.hasTag(tag)) {
                    isMatchingTags = false;
                    break;
                }
            }
            if (isMatchingTags) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }

    /**
     * Adds given tags to the Task at the given index.
     *
     * @param index The given index.
     * @return The Task with the tag added.
     * @throws DukeException If the index is out of range.
     */
    public Task tagTask(int index, String ...newTags) throws DukeException {
        if (index < 0 || index >= getSize()) {
            throw new DukeException("Invalid task number.");
        } else {
            Task task = taskList.get(index);
            for (String tag : newTags) {
                task.addTag(tag);
            }
            return task;
        }
    }

    /**
     * Removes given tags from the Task at the given index.
     *
     * @param index The given index.
     * @return The Task with the tag removed.
     * @throws DukeException If the index is out of range.
     */
    public Task untagTask(int index, String ...currentTags) throws DukeException {
        if (index < 0 || index >= getSize()) {
            throw new DukeException("Invalid task number.");
        } else {
            Task task = taskList.get(index);
            for (String tag : currentTags) {
                task.removeTag(tag);
            }
            return task;
        }
    }
}
