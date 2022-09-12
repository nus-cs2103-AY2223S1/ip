package duke;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Contains the list of Tasks stored in Duke, as well as methods to manipulate them.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Returns the list of Tasks stored in the TaskList.
     *
     * @return An ArrayList of Tasks stored in the TaskList.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of Tasks stored in the TaskList.
     * @return The amount of Tasks stored in the TaskList.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Returns an ArrayList of Tasks that contains the inputted string.
     *
     * @param s The string to be compared with.
     * @return An ArrayList of Tasks that contains the inputted string.
     */
    public ArrayList<Task> find(String s) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            String taskInLowercase = task.toString().toLowerCase();
            if (taskInLowercase.contains(s)) {
                result.add(task);
            }
        }
        return result;
    }

    /**
     * Returns the Task selected after marking it as complete.
     *
     * @param index The index of the Task to be marked, 1-indexed.
     * @return The marked Task.
     */
    public Task markTask(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Returns the Task selected after marking it as incomplete.
     *
     * @param index The index of the Task to be unmarked, 1-indexed.
     * @return The unmarked Task.
     */
    public Task unmarkTask(int index) {
        Task task = tasks.get(index);
        task.markAsUndone();
        return task;
    }

    /**
     * Removes the Task selected from the TaskList.
     *
     * @param index The index of the Task to be removed, 1-indexed.
     * @return The removed Task.
     */
    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Adds the Task inputted into the TaskList.
     *
     * @param task The task to add.
     * @return The added Task.
     */
    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Adds a Todo task into the TaskList.
     *
     * @param description Description of the Todo task.
     * @return The added Task.
     */
    public Task addTodo(String description) {
        Todo todo = new Todo(description);
        return addTask(todo);
    }

    /**
     * Adds a Deadline task into the TaskList.
     *
     * @param description Description of the Deadline task.
     * @param date Due date of the task.
     * @param time Due time of the task.
     * @return The added Task.
     */
    public Task addDeadline(String description, String date, String time) {
        Deadline deadline = new Deadline(description, date, time);
        return addTask(deadline);
    }

    /**
     * Adds an Event task into the TaskList.
     * @param description Description of the Event task.
     * @param dateStart Start date of the Event.
     * @param timeStart Start time of the Event.
     * @param dateEnd End date of the Event.
     * @param timeEnd End time of the Event.
     * @return The added Task.
     */
    public Task addEvent(String description,
                          String dateStart, String timeStart,
                          String dateEnd, String timeEnd) {
        Event event = new Event(description, dateStart, timeStart, dateEnd, timeEnd);
        return addTask(event);
    }
}
