package tuna.utility;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

import tuna.TunaException;
import tuna.task.Deadline;
import tuna.task.Event;
import tuna.task.Task;
import tuna.task.TimeBasedTask;
import tuna.task.Todo;

/**
 * Represents a task list to handle task related functionalities. A TaskList object contains an ArrayList of the
 * current tasks.
 */
public class TaskList {

    /** List of all the tasks */
    private ArrayList<Task> tasks;

    /**
     * Creates a TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index index of task to be returned.
     * @return task located at the specified index.
     */
    public Task getTask(int index) {
        assert index >= 0 && index < tasks.size();
        return tasks.get(index);
    }

    /**
     * Returns the total number of tasks.
     *
     * @return total number of tasks.
     */
    public int getTotalTasks() {
        return tasks.size();
    }

    /**
     * Returns the latest task to be added.
     *
     * @return latest task to be added.
     */
    public Task getLatestTask() {
        return tasks.get(tasks.size() - 1);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index index of the task to be deleted.
     */
    public void deleteTask(int index) {
        assert index >= 0 && index < tasks.size();
        tasks.remove(index);
    }

    /**
     * Adds a todo task to the list.
     *
     * @param taskDescription task description of the todo task.
     */
    public void addTodo(String taskDescription) {
        tasks.add(new Todo(taskDescription));
    }

    /**
     * Adds a deadline task to the list.
     *
     * @param taskDescription task description of the deadline task.
     * @param by deadline of the task.
     * @throws TunaException Exception thrown when the date and time provided is not formatted correctly.
     */
    public void addDeadLine(String taskDescription, String by) throws TunaException {
        tasks.add(new Deadline(taskDescription, by));
    }

    /**
     * Adds an event task to the list.
     *
     * @param taskDescription task description of the event task.
     * @param at start time of the event.
     * @throws TunaException Exception thrown when the date and time provided is not formatted correctly.
     */
    public void addEvent(String taskDescription, String at) throws TunaException {
        tasks.add(new Event(taskDescription, at));
    }

    /**
     * Lists all the tasks in the task list.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> listTasks() {
        return new ArrayList<Task>(tasks);
    }

    /**
     * Lists all the tasks that fall on the specified date.
     *
     * @param date date of tasks to be listed.
     * @return ArrayList of tasks occurring on the specified date.
     */
    public ArrayList<Task> listTasks(LocalDate date) {
        ArrayList<Task> tasksToList = new ArrayList<Task>();
        for (Task task : tasks) {
            if (!task.getTaskType().equals("T")) {
                if (task.getTaskType().equals("E")) {
                    if (LocalDate.parse(((Event) task).getStringRepresentationOfDateTime().split(" ")[0])
                            .equals(date)) {
                        tasksToList.add(task);
                    }
                } else if (task.getTaskType().equals("D")) {
                    if (LocalDate.parse(((Deadline) task).getStringRepresentationOfDateTime().split(" ")[0])
                            .equals(date)) {
                        tasksToList.add(task);
                    }
                }
            }
        }
        return tasksToList;
    }

    /**
     * Marks a task as done.
     *
     * @param index index of the task to be marked.
     */
    public void markItem(int index) {
        assert index >= 0 && index < tasks.size();
        tasks.get(index).markAsDone();
    }

    /**
     * Un-marks a task as done.
     *
     * @param index index of the task to be un-marked.
     */
    public void unMarkItem(int index) {
        assert index >= 0 && index < tasks.size();
        tasks.get(index).markAsNotDone();
    }

    /**
     * Finds and prints tasks which contain the specified keyword.
     *
     * @param keyword keyword to search for.
     * @return ArrayList of tasks which contain the specified keyword.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> tasksFound = new ArrayList<Task>();
        tasks.stream().filter((task) -> {
            return task.getDescription().contains(keyword);
        }).forEach((task) -> {
            tasksFound.add(task);
        });
        return tasksFound;
    }

    /**
     * Sorts the tasks in the tasks list in chronological order.
     */
    public void sort() {
        ArrayList<Task> todoTasks = getTodoTasks();
        ArrayList<TimeBasedTask> timeBasedTasks = getTimeBasedTasks();
        timeBasedTasks.sort(Comparator.comparing(TimeBasedTask::getDateTime));
        tasks.clear();
        tasks.addAll(timeBasedTasks);
        tasks.addAll(todoTasks);
    }

    /**
     * Filters out Todo tasks from the tasks list.
     *
     * @return list of todo tasks.
     */
    private ArrayList<Task> getTodoTasks() {
        ArrayList<Task> todoTasks = new ArrayList<Task>();
        for (Task task : tasks) {
            String taskType = task.getTaskType();
            if (taskType == "T") {
                todoTasks.add(task);
            }
        }
        return todoTasks;
    }

    /**
     * Filters out time based tasks from the tasks list.
     *
     * @return list of time based tasks.
     */
    private ArrayList<TimeBasedTask> getTimeBasedTasks() {
        ArrayList<TimeBasedTask> timeBasedTasks = new ArrayList<TimeBasedTask>();
        for (Task task : tasks) {
            String taskType = task.getTaskType();
            if (taskType.equals("D") || taskType.equals("E")) {
                timeBasedTasks.add((TimeBasedTask) task);
            }
        }
        return timeBasedTasks;
    }
}
