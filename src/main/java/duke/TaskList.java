package duke;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;

import java.util.ArrayList;

/**
 * Encapsulates the data structure storing Duke's tasks,
 * along with its functions to manipulate said data structure.
 */
public class TaskList {
    /** ArrayList to store Duke's tasks */
    private ArrayList<Task> db;

    /** Needed as the first task is listed as index 1 in the GUI. */
    static final int OFF_BY_ONE = 1;

    /**
     * Constructor for empty TaskList.
     */
    public TaskList() {
         db = new ArrayList<>(10);
    }

    /**
     * Initializes a TaskList from a String array passed in by the Storage class.
     * @param inputList String array containing tasks
     */
    public TaskList(ArrayList<String[]> inputList) {
        db = new ArrayList<>(10);
        for (String[] tmp: inputList) {
            switch (tmp[0]) {
                case "T":
                    db.add(new Todo(tmp));
                    break;
                case "D":
                    db.add(new Deadline(tmp));
                    break;
                case "E":
                    db.add(new Event(tmp));
                    break;
            }
        }
    }

    /**
     * Returns the number of tasks in the TaskList.
     * @return number of Tasks in TaskList
     */
    public int getLength() {
        return db.size();
    }

    /**
     * Returns a specified Task from TaskList based on its index (1-indexed).
     * @param i index of Task to retrieve
     * @return Task
     */
    public Task getTask(int i) {
        return db.get(i - OFF_BY_ONE);
    }

    /**
     * Marks the input task as completed.
     * @param userInput Input task number
     */
    public void mark(int userInput) {
        Task tmp = db.get(userInput - OFF_BY_ONE);
        tmp.setDone();
    }

    /**
     * Marks the input task as incomplete.
     * @param userInput Input task number
     */
    public void unmark(int userInput) {
        Task tmp = db.get(userInput - OFF_BY_ONE);
        tmp.setUndone();
    }

    /**
     * Adds a new generic Task.
     * @param task Task to be added
     */
    public void addTask(Task task) {
        db.add(task);
    }

    /**
     * Adds a new To-Do task.
     * @param task To-do to be added
     */
    public void addTodo(Todo task) {
        db.add(task);
    }

    /**
     * Adds a new Event task.
     * @param task Event to be added
     */
    public void addEvent(Event task) {
        db.add(task);
    }

    /**
     * Adds a new Deadline task.
     * @param task Deadline to be added
     */
    public void addDeadline(Deadline task) {
        db.add(task);
    }

    /**
     * Deletes a specified task from Duke's memory.
     * @param userInput Task number to be deleted
     * @return Deleted task
     */
    public Task delete(int userInput) {
        return db.remove(userInput - OFF_BY_ONE);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < db.size(); i++) {
            res.append(i + OFF_BY_ONE).append(". ").append(db.get(i).toString()).append("\n");
        }
        return res.toString().trim();
    }

    /**
     * Returns a string representation of the Tasks in TaskList for writing to Duke's save file.
     * @return Tasks to write to save file
     */
    public String[] toStringWritable() {
        String[] res = new String[db.size()];
        for (int i = 0; i < db.size(); i++) {
            res[i] = db.get(i).toStringWritable();
        }
        return res;
    }
}
