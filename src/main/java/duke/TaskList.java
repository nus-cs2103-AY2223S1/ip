package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import duke.task.Task;


/**
 * The TaskList class contains the task list.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> fromStorage) {
        this.taskList = fromStorage;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public int getTasksNumber() {
        return taskList.size();
    }

    /**
     * Adds the Task into the storage.
     *
     * @param t Specified Task.
     */
    public void addTask(Task t) {
        this.taskList.add(t);
    }

    /**
     * Deletes the specified task in the storage.
     *
     * @param i Specified task.
     */
    public Task deleteTask(int i) throws DukeException {
        try {
            Task target = taskList.get(i - 1);
            taskList.remove(target);
            return target;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task not found.");
        }
    }

    /**
     * Gets the tasks on a specified date.
     *
     * @param date LocalDate object that represents specified date.
     * @return ArrayList that represents all tasks on specified date.
     */
    public ArrayList<Task> getTasksOnDate(LocalDate date) {
        ArrayList<Task> tasksOnDate = taskList.stream()
                .filter(x -> x.isSameDate(date))
                .collect(Collectors.toCollection(ArrayList::new));

        return tasksOnDate;
    }

    /**
     * Find tasks by a specified keyword.
     * @param keyword Specified keyword.
     * @return ArrayList that represents all tasks with keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> tasksFound = taskList.stream()
                .filter(x -> x.toString().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
        return tasksFound;
    }

    /**
     * Mark the specified task in the storage as done.
     * @param i Specified task.
     */
    public Task markDone(int i) throws DukeException {
        try {
            Task target = taskList.get(i - 1);
            target.markDone();
            return target;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task not found.");
        }
    }

    /**
     * Mark the specified task in the storage as not done.
     * @param i Specified task
     */
    public Task unmarkDone(int i) throws DukeException {
        try {
            Task target = taskList.get(i - 1);
            target.unmarkDone();
            return target;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task not found");
        }
    }

    /**
     * Attach the specified Task with the Priority specified.
     *
     * @param i Specified Task.
     * @param priority Specified Priority.
     * @return Task with attached Priority.
     * @throws DukeException when Task is not found.
     */
    public Task attachPriority(int i, String priority) throws DukeException {
        try {
            Task target = taskList.get(i - 1);
            target.attachPriority(priority);
            return target;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task not found");
        }
    }
}
