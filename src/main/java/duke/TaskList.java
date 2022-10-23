package duke;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.stream.Collectors;

/**
 * Represents the collection of Tasks set by a user of Duke, stored within a ArrayList.
 */
public class TaskList {
    private final List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Updates a Task at a given index in the collection as complete.
     *
     * @return Task updated as complete.
     */
    protected Task markTask(int index) {
        Task completedTask = tasks.get(index).completeTask();
        tasks.set(index, completedTask);
        return completedTask;
    }

    /**
     * Updates a Task at a given index in the collection as incomplete.
     *
     * @return Task updated as incomplete.
     */
    protected Task unmarkTask(int index) {
        Task incompleteTask = tasks.get(index).resetTask();
        tasks.set(index, incompleteTask);
        return incompleteTask;
    }

    /**
     * Adds a new Task to the collection of Tasks set belonging to the user.
     *
     * @param newTask new Task created by user.
     */
    protected void add(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Removes a Task at a given index from the collection of Tasks set belonging to the user.
     *
     * @param index position of task in the collection of Tasks.
     */
    protected Task delete(int index) {
        return tasks.remove(index);
    }

    protected int getSize() {
        return tasks.size();
    }

    /**
     * Reads the collection of Tasks belonging to the user, and then formats it as a String where
     * each task is labeled numerically.
     *
     * @return String consisting of all Tasks belonging to the user.
     */
    protected String enumerateList() {
        return IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + ". " + tasks.get(i) + "\n")
                .reduce("", (task1, task2) -> task1 + task2);
    }

    /**
     * Filters through the current TaskList belonging to the user to find tasks containing a given
     * search term, then collects the matching tasks in String format where each task is labeled numerically.
     *
     * @param term Search term provided by the user.
     * @return String consisting of all Tasks containing the given search term.
     */
    protected String findTasks(String term) {
        List<Task> filteredTaskList = tasks.stream()
                .filter(task -> task.getDesc().contains(term))
                .collect(Collectors.toList());
        return new TaskList(filteredTaskList).enumerateList();
    }
}
