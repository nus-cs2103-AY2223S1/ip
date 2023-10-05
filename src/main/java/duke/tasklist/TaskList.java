package duke.tasklist;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * Represents a lists of Tasks
 */
public class TaskList {
    private List<Task> tasks;
    private static final String DUPLICATE_MESSAGE = "This task is a duplicate";

    /**
     * Instantiates a new TaskList with empty list
     */
    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    /**
     * Instantiates a new TaskList with given
     * List of tasks
     *
     * @param tasks List of tasks
     */
    public TaskList(List<Task> tasks) {
        assert tasks != null : "tasks given cannot be null";
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks
     *
     * @return the list of tasks of this TaskList object
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns the list of tasks in string format
     *
     * @return the list of tasks of this TaskList object
     * in String format
     */
    public String listTask() {
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            output += String.format("%d.%s\n", i + 1, currTask);
        }
        return output;
    }

    /**
     * Returns the number of task in the TaskList
     *
     * @return the number of task in the TaskList
     */
    public int numOfTasks() {
        return tasks.size();
    }

    /**
     * Adds task to list tasks and return a String representation
     * of the task
     *
     * @param task the task that we want to add to the list
     * @return string output of what task is added to the list
     */
    public String addTask(Task task) throws DukeException {
        assert task != null : "task to add to task list cannot be null";
        //check for duplicates
        for (int i = 0; i < tasks.size(); i++) {
            if (task.equals(tasks.get(i))) {
                throw new DukeException(DUPLICATE_MESSAGE);
            }
        }
        tasks.add(task);
        String output = String.format("%s\n", task);
        return output;
    }

    /**
     * Deletes task from list tasks and return a String representation
     * of the task
     *
     * @param index position of task in which we want to remove
     * @return string output of what task is deleted from the list
     * @throws DukeException  If tasks.size() == 0.
     */
    public String deleteTask(int index) throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("Tasklist is empty");
        }
        assert index >=0 && index < tasks.size(): "index of element to be removed must be non-negative " +
                "and smaller than size of list";
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException(invalidAction("delete"));
        }
        Task currTask = tasks.remove(index);
        String output = String.format("%s\n", currTask);
        return output;
    }


    /**
     * Marks task from list tasks and return a String representation
     * of the task
     *
     * @param index position of task in which we want to mark
     * @return string output of what task is marked in the list
     * @throws DukeException  If index < 0 || index >= tasks.size().
     */
    public String mark(int index) throws DukeException {
        assert index >=0 && index < tasks.size(): "index of element to be marked must be non-negative " +
                "and smaller than size of list";
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException(invalidAction("mark"));
        }
        Task currTask = tasks.get(index);
        currTask.mark();
        String message = String.format("%s\n", currTask);
        return message;
    }

    /**
     * Unmarks task from list tasks and return a String representation
     * of the task
     *
     * @param index position of task in which we want to unmark
     * @return string output of what task is unmarked in the list
     * @throws DukeException  If index < 0 || index >= tasks.size().
     */
    public String unmark(int index) throws DukeException {
        assert index >=0 && index < tasks.size(): "index of element to be unmarked must be non-negative " +
                "and smaller than size of list";
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException(invalidAction("unmark"));
        }
        Task currTask = tasks.get(index);
        currTask.unmark();
        String message = String.format("%s\n", currTask);
        return message;
    }

    /**
     * Finds all task in the list that match with the keyword
     *
     * @param keyword keyword input by user
     * @return String representation of all the tasks that
     * contain they keyword
     */
    public String find(String keyword) {
        assert keyword != null: "keyword used for searching cannot be null";
        int index = 1;
        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (currTask.getDescription().contains(keyword)) {
                output += String.format("%d.%s\n", index, currTask);
                index++;
            }
        }
        return output;
    }


    /**
     * Returns a String representation of error message to be thrown for DukeException
     * for different message given in the input.
     *
     * @param message String representation of keywords such as mark, unmark and delete
     * @return String representation error message to be thrown for DukeException
     */
    public static String invalidAction(String message) {
        if (message.equals("")) {
            return "Invalid command, I don't know what that means :-(";
        }
        return String.format("It must be in the format of: %s <position in list>", message);
    }
}
