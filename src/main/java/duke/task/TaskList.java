package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Task List for Duke application.
 *
 * @author Farrel Dwireswara Salim
 */
public class TaskList {
    private List<Task> tasks;

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
        if (tasks.isEmpty()) {
            return "You have no tasks at the moment.";
        }

        String tasksString = "";
        for (int i = 0; i < tasks.size(); i++) {
            tasksString += String.format("%d. %s\n", i + 1, this.tasks.get(i));
        }
        return "Here are the tasks in your list\n" + tasksString;
    }

    /**
     * Transforms each Task in the TaskList to a string that is compatible to Duke's storage.
     *
     * @return the list of string to be saved in storage.
     */
    public List<String> toStorageRepresentation() {
        return this.tasks.stream()
                .map(Task::toStorageRepresentation)
                .collect(Collectors.toList());
    }
}
