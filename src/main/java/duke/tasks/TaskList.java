package duke.tasks;

import duke.exceptions.DukeException;
import duke.exceptions.DukeOutOfRangeException;
import duke.parser.Parser;
import duke.storage.Storage;

import java.util.ArrayList;

/**
 * Encapsulates a list of tasks in Duke
 */
public class TaskList {

    protected ArrayList<Task> tasks;
    protected Storage storage;

    /**
     * Initialises the tasks array
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initialises the tasks array by using existing data stored in
     * the local machine
     *
     * @param data An ArrayList of strings containing the saved user data
     */
    public TaskList(ArrayList<? extends String> data) {
        assert(data != null);
        this.tasks = new ArrayList<>();
        try {
            for (String sentence : data) {
                this.tasks.add(Parser.parseTask(sentence));
            }
        } catch (DukeException e) {
            this.tasks = new ArrayList<>();
        }
    }

    /**
     * Shows the list of tasks currently added to the TaskList
     *
     * @return a string containing the tasks added to the TaskList separated
     *     by a newline character
     */
    public String showList() {
        String str = "";
        assert(tasks != null);
        for (int i = 0; i < tasks.size() - 1; i++) {
            str += ((i + 1) + "." + tasks.get(i) + "\n");
        }
        if (tasks.size() != 0) {
            str += (tasks.size() + "." + tasks.get(tasks.size() - 1));
        }
        return str;
    }

    /**
     * Marks the task in the index-th position of the tasks array
     * as done.
     *
     * @param index the position of the task in the tasks array
     * @throws DukeException if index exceeds the size of the tasks array
     *     or if index is less than or equal to 0
     */
    public void markAsDone(int index) throws DukeException {
        if (index < 1 || index > this.tasks.size()) {
            throw new DukeOutOfRangeException(this.tasks.size());
        }
        assert(index > 1 && index <= this.tasks.size());
        Task task = this.tasks.get(index - 1);
        task.markAsDone();
    }

    /**
     * Marks the task in the index-th position of the tasks array
     * as not done.
     *
     * @param index the position of the task in the tasks array
     * @throws DukeException if index exceeds the size of the tasks array
     *     or if index is less than or equal to 0
     */
    public void markAsNotDone(int index) throws DukeException {
        if (index < 1 || index > this.tasks.size()) {
            throw new DukeOutOfRangeException(this.tasks.size());
        }
        assert(index > 1 && index <= this.tasks.size());
        Task task = this.tasks.get(index - 1);
        task.markAsNotDone();
    }

    /**
     * Adds a task to the ArrayList of tasks
     *
     * @param task the task to be added to the list of tasks
     */
    public void add(Task task) {
        assert(task != null);
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the tasks array
     *
     * @param index the position of the task in the tasks array
     * @throws DukeException if index exceeds the size of the tasks array
     *      or if index is less than or equal to 0
     */
    public void deleteTask(int index) throws DukeException {
        if (index < 1 || index > this.tasks.size()) {
            throw new DukeOutOfRangeException(this.tasks.size());
        }
        assert(index > 1 && index <= this.tasks.size());
        Task task = this.tasks.get(index - 1);;
        this.tasks.remove(index - 1);
    }

    /**
     * Shows the number of tasks in the TaskList
     *
     * @return the number of tasks in the TaskList
     */
    public String showNumberOfTasks() {
        return "\nNow you have " + this.tasks.size() + " tasks in the list.";
    }

    /**
     * Retrieves the task in the index-th position of the tasks array
     *
     * @param index the position of the task in the tasks array
     * @return the task in the index-th position of the tasks array
     * @throws DukeException if index exceeds the size of the tasks array
     *      or if index is less than or equal to 0
     */
    public String getTask(int index) throws DukeException {
        if (index < 1 || index > this.tasks.size()) {
            throw new DukeOutOfRangeException(this.tasks.size());
        }
        assert(index > 1 && index <= this.tasks.size());
        assert(tasks.get(index - 1) != null);
        return this.tasks.get(index - 1).toString();
    }

    /**
     * Returns the tasks ArrayList
     *
     * @return the tasks ArrayList
     */
    public ArrayList<Task> toArrayList() {
        assert(tasks != null);
        return tasks;
    }

    /**
     * Shows a list that is filtered by the keyword
     */
    public String showFilteredList(String keyword) {
        ArrayList<Task> filteredList = filterList(keyword);
        String result = changeListToString(filteredList);
        if (result == "") {
            return String.format("Your %s keyword does not match any entries!", keyword);
        }
        return result;
    }

    private ArrayList<Task> filterList(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).checkIfContains(keyword)) {
                filteredTasks.add(tasks.get(i));
            }
        }
        return filteredTasks;
    }

    private String changeListToString(ArrayList<? extends Task> tasks) {
        String str = "";
        for (int i = 0; i < tasks.size() - 1; i++) {
            str += ((i + 1) + "." + tasks.get(i) + "\n");
        }
        if (tasks.size() != 0) {
            str += (tasks.size() + "." + tasks.get(tasks.size() - 1));
        }
        return str;
    }
}
