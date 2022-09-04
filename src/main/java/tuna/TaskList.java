package tuna;

import java.time.LocalDate;
import java.util.ArrayList;

import tuna.task.Deadline;
import tuna.task.Event;
import tuna.task.Task;
import tuna.task.Todo;

/**
 * Represents a task list to handle task related functionalities. A TaskList object contains an ArrayList of the
 * current tasks.
 */
public class TaskList {

    /** List of all the tasks */
    private ArrayList<Task> data;

    /**
     * Creates a TaskList object.
     */
    public TaskList() {
        this.data = new ArrayList<Task>();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index index of task to be returned.
     * @return task located at the specified index.
     */
    public Task getTask(int index) {
        assert index >= 0 && index < data.size();
        return data.get(index);
    }

    /**
     * Returns the total number of tasks.
     *
     * @return total number of tasks.
     */
    public int getTotalTasks() {
        return data.size();
    }

    /**
     * Returns the latest task to be added.
     *
     * @return latest task to be added.
     */
    public Task getLatestTask() {
        return data.get(data.size() - 1);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index index of the task to be deleted.
     */
    public void deleteTask(int index) {
        assert index >= 0 && index < data.size();
        data.remove(index);
    }

    /**
     * Adds a todo task to the list.
     *
     * @param taskDescription task description of the todo task.
     */
    public void addTodo(String taskDescription) {
        data.add(new Todo(taskDescription));
    }

    /**
     * Adds a deadline task to the list.
     *
     * @param taskDescription task description of the deadline task.
     * @param by deadline of the task.
     */
    public void addDeadLine(String taskDescription, String by) {
        data.add(new Deadline(taskDescription, by));
    }

    /**
     * Adds an event task to the list.
     *
     * @param taskDescription task description of the event task.
     * @param at start time of the event.
     */
    public void addEvent(String taskDescription, String at) {
        data.add(new Event(taskDescription, at));
    }

    /**
     * Lists all the tasks in the task list.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> listTasks() {
        return new ArrayList<Task>(data);
    }

    /**
     * Lists all the tasks that fall on the specified date.
     *
     * @param date date of tasks to be listed.
     * @return ArrayList of tasks occurring on the specified date.
     */
    public ArrayList<Task> listTasks(LocalDate date) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        for (Task task : data) {
            if (!task.getTaskType().equals("T")) {
                if (task.getTaskType().equals("E")) {
                    if (LocalDate.parse(((Event) task).getAt().split(" ")[0]).equals(date)) {
                        tasks.add(task);
                    }
                } else if (task.getTaskType().equals("D")) {
                    if (LocalDate.parse(((Deadline) task).getBy().split(" ")[0]).equals(date)) {
                        tasks.add(task);
                    }
                }
            }
        }
        return tasks;
    }

    /**
     * Marks a task as done.
     *
     * @param index index of the task to be marked.
     */
    public void markItem(int index) {
        assert index >= 0 && index < data.size();
        data.get(index).markAsDone();
    }

    /**
     * Un-marks a task as done.
     *
     * @param index index of the task to be un-marked.
     */
    public void unMarkItem(int index) {
        assert index >= 0 && index < data.size();
        data.get(index).markAsNotDone();
    }

    /**
     * Finds and prints tasks which contain the specified keyword.
     *
     * @param keyword keyword to search for.
     * @return ArrayList of tasks which contain the specified keyword.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> tasksFound = new ArrayList<Task>();
        for (Task task : data) {
            if (task.getDescription().contains(keyword)) {
                tasksFound.add(task);
            }
        }
        return tasksFound;
    }
}
