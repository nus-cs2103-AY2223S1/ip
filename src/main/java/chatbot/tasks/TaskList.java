package chatbot.tasks;

import chatbot.exceptions.DukeException;

import java.time.LocalDate;
import java.util.*;

/**
 * The class contains two fields. The first "todos" is a list containing
 * all the user's tasks. The second taskByDates contains all the user's tasks
 * that are time sensitive grouped by their dates.
 *
 * It provides methods to access and manipulate the data encapsulated (CRUD operations).
 */
public class TaskList {
    private List<Task> todos = new ArrayList<>();
    private HashMap<LocalDate, TaskBucket> taskByDates = new HashMap<>();

    public int getNumberOfTasks() {
        return todos.size();
    }

    /**
     * The method allows user to add a Todo to their todo list.
     *
     * @param taskName Name of the task.
     * @return The corresponding Todo task.
     */
    public Task addTodo(String taskName) {
        Task newTask = new Todo(taskName);
        todos.add(newTask);
        return newTask;
    }

    /**
     * The method allows user to add a Deadline task to their todo list.
     *
     * @param taskName Name of the task.
     * @param date Date that the task should be completed by.
     * @return The corresponding Deadline task.
     */
    public Task addDeadline(String taskName, LocalDate date) {
        Task newTask = new Deadline(taskName, date);
        todos.add(newTask);
        bucketTasks(date, newTask);
        return newTask;
    }

    /**
     * The method allows user to add an Event to their todo list.
     *
     * @param taskName Name of the event.
     * @param date Date that the event is happening.
     * @return The corresponding Event.
     */
    public Task addEvent(String taskName, LocalDate date) {
        Task newTask = new Event(taskName, date);
        todos.add(newTask);
        bucketTasks(date, newTask);
        return newTask;
    }

    /**
     * Getter for all the tasks.
     *
     * @return The list that contains all the user's tasks.
     */
    public List<Task> listTasks() {
        return this.todos;
    }

    /**
     * Gets the time sensitive tasks on the specified date.
     *
     * @param date
     * @return The list containing all the relevant task.
     */
    public List<Task> getTaskOn(LocalDate date) {
        if (taskByDates.containsKey(date)) {
            return taskByDates.get(date).getTasks();
        } else {
            return null;
        }
    }

    /**
     * The method allows user to delete a given task from the todo list.
     *
     * @param index The index of the task to be deleted.
     * @throws DukeException If the index to be deleted does not exist.
     */
    public Task deleteTask(int index) throws DukeException {
        try {
            return todos.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw DukeException.INVALID_TASK_INDEX;
        }
    }

    /**
     * The method allows user to mark a task as completed.
     *
     * @param index The task to be marked as completed.
     * @throws DukeException If no task exists at the provided index.
     */
    public Task markTask(int index) throws DukeException {
        try {
            Task target = todos.get(index - 1);
            target.mark();
            return target;
        } catch (IndexOutOfBoundsException e) {
            throw DukeException.INVALID_TASK_INDEX;
        }
    }

    /**
     * The method allows user to mark a task as incomplete.
     *
     * @param index The task to be marked as incomplete.
     * @throws DukeException If no task exists at the provided index.
     */
    public Task unmarkTask(int index) throws DukeException {
        try {
            Task target = todos.get(index - 1);
            target.unmark();
            return target;
        } catch (IndexOutOfBoundsException e) {
            throw DukeException.INVALID_TASK_INDEX;
        }
    }

    /**
     * Finds all the tasks in the todo list containing the keyword.
     *
     * @param keyword The keyword in user's search
     * @return The list of tasks containing the keyword.
     */
    public List<Task> find(String keyword) {
        List<Task> matches = new ArrayList<>();
        for (Task task : todos) {
            if (task.getTaskName().contains(keyword)) {
                matches.add(task);
            }
        }

        return matches;
    }

    /**
     * The method assigns the task to a group by its date.
     *
     * @param date The date associated with the task.
     * @param task The task to be assigned.
     */
    private void bucketTasks(LocalDate date, Task task) {
        if (taskByDates.containsKey(date)) {
            taskByDates.get(date).addTask(task);
        } else {
            TaskBucket newBucket = new TaskBucket();
            newBucket.addTask(task);
            taskByDates.put(date, newBucket);
        }
    }
}
