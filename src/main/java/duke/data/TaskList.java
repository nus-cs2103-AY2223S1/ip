package duke.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import duke.data.exception.DukeException;
import duke.tasks.Task;

/**
 * This class encapsulates a list of tasks
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a new Task List
     * @param tasks The list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Lists down all the tasks that are added to the list.
     * @return A list of all the tasks added.
     */
    public ArrayList<Task> list() {
        return tasks;
    }

    /**
     * Gets all the task that falls on the specified date
     * @param date Date of the tasks
     * @return A string consisting of all the tasks
     */
    public List<Task> list(String date) {
        Predicate<Task> isTaskValid = task -> {
            LocalDate parsedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            boolean hasValidTaskType = task.getTaskType().equals("D") || task.getTaskType().equals("E");
            boolean hasCorrectDate = task.getDate() != null && task.getDate().equals(parsedDate);
            return hasValidTaskType && hasCorrectDate;
        };

        List<Task> results = tasks.stream().filter(isTaskValid).collect(Collectors.toList());

        return results;
    }

    /**
     * Adds a task to the list
     * @param task The task to be added
     */
    public void addToList(Task task) {
        tasks.add(task);
    }

    /**
     * Gets the number of tasks in the list
     * @return The number of tasks in the list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Gets the task at the specified index
     * @param i Index of the task
     * @return The task with the specified index
     * @throws DukeException If the task does not exist
     */
    public Task getTask(int i) throws DukeException {
        if (i > tasks.size() || i < 0) {
            throw new DukeException("No such task exists!");
        }

        return tasks.get(i);
    }

    /**
     * Deletes the task at the specified index
     * @param i The index of the task (1-based indexing)
     * @return The deleted task
     * @throws DukeException If the task does not exist
     */
    public Task deleteTask(int i) throws DukeException {
        if (i > tasks.size() || i <= 0) {
            throw new DukeException("No such task exist!");
        }

        Task task = tasks.get(i - 1);
        tasks.remove(i - 1);
        return task;
    }

    /**
     * Finds the tasks based on the given keyword
     * @param keywords Keyword used to search for tasks
     * @return A list of tasks containing the keyword provided
     */
    public List<Task> find(String ...keywords) {
        List<Task> results = tasks.stream()
                .filter(task -> containsAllKeywords(task.getDescription(), keywords))
                .collect(Collectors.toList());

        return results;
    }

    private boolean containsAllKeywords(String description, String ...keywords) {
        long numOfKeywords = Arrays.stream(keywords)
                .filter(description::contains)
                .count();

        return numOfKeywords == keywords.length;

    }

    /**
     * Gets all the lists required to summarise user's activities
     * @param start Start date
     * @param end End date
     * @return An array of tasks list
     */
    public List<?>[] summary(LocalDate start, LocalDate end) {
        List<Task> tasksWithinDateRange = getTasksWithinDateRange(start, end);
        List<Task> completedTasks = getAllCompletedTasks();
        List<Task> upcomingTasks = getUpcomingTasks();
        return new List<?>[] {tasksWithinDateRange, completedTasks, upcomingTasks};
    }

    private List<Task> getTasksWithinDateRange(LocalDate start, LocalDate end) {
        Predicate<Task> isWithinRange = task -> {
            if (task.getDateMarked() == null) {
                return false;
            }

            LocalDate dateMarked = task.getDateMarked();
            boolean isBefore = dateMarked.isBefore(start);
            boolean isAfter = dateMarked.isAfter(end);
            return !isBefore && !isAfter;
        };

        List<Task> tasksWithinDateRange = tasks.stream().filter(isWithinRange).collect(Collectors.toList());

        return tasksWithinDateRange;
    }

    private List<Task> getAllCompletedTasks() {
        List<Task> results = tasks.stream().filter(Task::getIsDone).collect(Collectors.toList());
        return results;
    }

    private List<Task> getUpcomingTasks() {
        Predicate<Task> isUpcoming = task -> {
            if (task.getDate() == null) {
                return false;
            }
            boolean isAfter = task.getDate().isAfter(LocalDate.now());
            boolean isCompleted = task.getIsDone();
            return isAfter && isCompleted;
        };

        List<Task> results = tasks.stream().filter(isUpcoming).collect(Collectors.toList());
        return results;
    }
}
