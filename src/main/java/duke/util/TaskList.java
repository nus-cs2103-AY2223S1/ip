package duke.util;

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

    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the current List of Task.
     * 
     * @return List<Task>
     */
    public List<Task> getTasks() {
        return tasks;
    };

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
