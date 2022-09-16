package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.FileParseException;
import duke.task.Task;

/**
 * Handles anything that is related to TaskList and Tasks.
 */
public class TaskList {
    private ArrayList<Task> listOfTasks;
    private final Storage storage;

    /**
     * Constructor for a new TaskList when save file exists.
     * @param storage Storage object when loading from save file.
     */
    public TaskList(Storage storage) {
        this.listOfTasks = new ArrayList<>();
        Parser p = new Parser(this);
        this.storage = storage;
        try {
            ArrayList<String> fileInput = storage.load();
            if (!fileInput.isEmpty()) {
                for (String line : fileInput) {
                    try {
                        p.parseInput(line, true);
                    } catch (FileParseException e) {
                        Ui.warnCorruptedLine(e);
                    } catch (DukeException e) {
                        assert false;
                        Ui.showUnknownError();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            this.listOfTasks = new ArrayList<>();
        }
    }

    /**
     * Constructor for new empty TaskList when no save file exists.
     */
    public TaskList() {
        this.storage = new Storage("data" + File.separator + "taskList");
        this.listOfTasks = new ArrayList<>();
    }

    public ArrayList<Task> getArray() {
        return this.listOfTasks;
    }

    /**
     * Method to mark a Task as done.
     * @param i The index of the task as shown in the list.
     * @return The Task that was marked done.
     */
    public Task markAsDone(int i) {
        Task task = this.listOfTasks.get(i);
        task.setDone(true);
        return task;
    }

    /**
     * Method to mark a Task as not done.
     * @param i The index of the task as shown in the list.
     * @return The Task that was marked not done.
     */
    public Task markAsNotDone(int i) {
        Task task = this.listOfTasks.get(i);
        task.setDone(false);
        return task;
    }

    public Task delete(int i) {
        return this.listOfTasks.remove(i);
    }

    public void add(Task task) {
        this.listOfTasks.add(task);
    }

    public int getSize() {
        return this.listOfTasks.size();
    }

    /**
     * Method to search for Tasks containing the keyword.
     * @param keyword The search query.
     * @return An ArrayList of Tasks that contains the keyword.
     */
    public ArrayList<Task> searchFor(String keyword) {
        ArrayList<Task> output = new ArrayList<>();
        for (Task t : this.listOfTasks) {
            if (t.getDescription().contains(keyword)) {
                output.add(t);
            }
        }
        return output;
    }

    public Task getTask(int index) {
        return this.listOfTasks.get(index);
    }

    public Task lastTaskAdded() {
        return this.listOfTasks.get(this.listOfTasks.size() - 1);
    }

    public void save() {
        storage.saveToFile(this);
    }

}
