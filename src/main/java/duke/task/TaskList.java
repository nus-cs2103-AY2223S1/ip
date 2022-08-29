package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Task List for Duke application.
 *
 * @author Farrel Dwireswara Salim
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs a new empty TaskList instance.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList instance based on a list of Tasks.
     *
     * @param tasks the list of Tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the size of the TaskList.
     *
     * @return the number of Tasks in the TaskList.
     */
    public int getTaskListSize() {
        return this.tasks.size();
    }

    /**
     * Returns true if the TaskList is empty.
     *
     * @return true if the current TaskList is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.tasks == null || this.tasks.isEmpty();
    }

    /**
     * Adds a given Task to the TaskList.
     *
     * @param newTask Task to be added.
     */
    public void addTask(Task newTask) {
        this.tasks.add(newTask);
    }

    /**
     * Marks the Task with given index in TaskList.
     *
     * @param index the index of the Task to be marked.
     * @return the newly marked Task.
     */
    public Task markTaskWithIndex(int index) {
        Task selectedTask = this.tasks.get(index);
        selectedTask.markAsFinished();
        return selectedTask;
    }

    /**
     * Unmarks the Task with given index in TaskList.
     *
     * @param index the index of the Task to be unmarked.
     * @return the newly unmarked Task.
     */
    public Task unmarkTaskWithIndex(int index) {
        Task selectedTask = this.tasks.get(index);
        selectedTask.markAsNotFinished();
        return selectedTask;
    }

    /**
     * Removes the Task with given index in TaskList.
     *
     * @param index the index of the Task to be removed.
     * @return the newly removed Task.
     */
    public Task removeTaskWithIndex(int index) {
        Task selectedTask = this.tasks.get(index);
        this.tasks.remove(index);
        return selectedTask;
    }

    /**
     * Gets the string representation of the TaskList.
     *
     * @return the string which represents the current TaskList.
     */
    @Override
    public String toString() {
        StringBuilder tasksString = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            tasksString.append(String.format("%d. %s\n", i + 1, this.tasks.get(i)));
        }
        return tasksString.toString();
    }

    /**
     * Filters current TaskList to get all Tasks that matches the keyword.
     *
     * @param keyWords the strings of keyword.
     * @return the TaskList which contains all the matched Tasks.
     */
    public TaskList filterByKeyWord(String ... keyWords) {
        return new TaskList(this.tasks.stream()
                .filter(task -> task.isContainKeyWord(keyWords))
                .collect(Collectors.toList()));
    }

    /**
     * Filters current TaskList to get all Tasks that happens on the selected date.
     *
     * @param selectedDates the selected dates.
     * @return the TaskList which contains all the matched Tasks.
     */
    public TaskList findByDate(LocalDate ... selectedDates) {
        return new TaskList(this.tasks.stream()
                .filter(task -> task.isOnGivenDate(selectedDates))
                .collect(Collectors.toList()));
    }

    /**
     * Transforms each Task in the TaskList to a string that is compatible
     * to Duke's storage.
     *
     * @return the list of string to be saved in storage.
     */
    public List<String> toStorageRepresentation() {
        return this.tasks.stream()
                .map(Task::toStorageRepresentation)
                .collect(Collectors.toList());
    }
}
