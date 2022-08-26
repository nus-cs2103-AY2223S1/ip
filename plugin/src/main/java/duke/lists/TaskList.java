package duke.lists;

import java.io.IOException;
import java.util.ArrayList;

import duke.entities.Task;
import duke.exceptions.DukeException;
import duke.utils.Storage;

/**
 * Stores task and acts as an intermediate layer between Duke and Task
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Storage fh;

    /**
     * Initialises the task list and the save file if
     * the save file does not already exist.
     * If the file exists, data will be read from it and loaded into the list.
     */
    public TaskList(String fname) throws DukeException {
        tasks = new ArrayList<Task>();
        try {
            fh = new Storage(tasks, fname);
            fh.loadFile();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Returns the Task at the given indx
     * @param indx Specifies the Task to return
     * @return The task at index
     */
    public Task get(int indx) {
        return tasks.get(indx);
    }

    /**
     * Adds a task to the task list
     * @param task Task to be added
     */
    public void addTask(Task task) throws DukeException {
        tasks.add(task);
        try {
            fh.writeToFile(task);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Removes task at the specified index
     * Also removes it from the saved txt file
     * @param indx of the tasks to be removed
     * @return the task removed
     */
    public Task removeTask(int indx) throws DukeException {
        Task deleted = tasks.remove(indx);
        try {
            fh.deleteFromFile(indx);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        return deleted;
    }

    /**
     * Marks task as done
     * @param indx of the task to be marked
     * @return the task marked
     */
    public Task markTask(int indx) throws DukeException, IOException {
        Task currentTask = tasks.get(indx);
        currentTask.toggleComplete();
        fh.markTask(indx, currentTask.toString());
        return currentTask;
    }

    /**
     * Returns the task that matches the given keyword
     * @param keyword
     * @return
     */
    public String find(String keyword) {
        String output = "";
        for (Task task : tasks) {
            if (task.contains(keyword)) {
                output += task;
                output += "\n";
            }
        }
        return output;
    }

    @Override
    public String toString() {
        String output = "";
        // Add task descriptions
        for (int i = 0; i < tasks.size(); i++) {
            output += (i + 1);
            output += " ";
            output += tasks.get(i);
            output += "\n";
        }
        return !output.equals("") ? output : "No tasks!";
    }
}
