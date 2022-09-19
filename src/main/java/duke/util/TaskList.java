package duke.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import duke.exceptions.OutOfBoundException;
import duke.task.Task;

/**
 * Tasklist to handle management of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Takes in an existing list of task.
     *
     * @param tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Makes an empty list of task
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Append the task to the bottom of the list.
     *
     * @param task
     */
    public void addEntry(Task task) {
        tasks.add(task);
    }

    /**
     * Delete the index's task and return it.
     *
     * @param index Index of task to delete
     * @return Task
     * @throws OutOfBoundException Throws when index is out of bound
     */
    public Task deleteEntry(int index) throws OutOfBoundException {
        if (index >= tasks.size() || index < 0) {
            throw new OutOfBoundException();
        }
        return tasks.remove(index);
    }

    /**
     * Returns the task at the given index.
     *
     * @param index Index of task to retrieve
     * @return Task
     * @throws OutOfBoundException Throws when index is out of bound
     */
    public Task get(int index) throws OutOfBoundException {
        if (index >= tasks.size() || index < 0) {
            throw new OutOfBoundException();
        }
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks currently.
     *
     * @return int
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the current List of Task.
     *
     * @return List of Tasks
     */
    public List<Task> getTasks() {
        return tasks;
    };

    /**
     * Returns a list of Tasks that is smaller than datetime.
     *
     * @dt time to be compared to
     * @return List of Tasks
     */
    public List<Task> getTaskBefore(LocalDateTime dt) {
        List<Task> cpy = new ArrayList<>(tasks);
        cpy.sort(null);
        List<Task> ret = new ArrayList<>();
        for (Task t : cpy) {
            if (t.compareTo(dt) > 0) {
                break;
            }
            ret.add(t);
        }
        return ret;
    }

    /**
     * Converts current tasks to ParsedData[] to be saved.
     *
     * @return ParsedData[]
     */
    public ParsedData[] getParsedData() {
        ParsedData[] ret = new ParsedData[tasks.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = tasks.get(i).convertToParseData();
        }
        return ret;
    }
}
