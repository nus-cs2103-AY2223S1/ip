package duke;

import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;
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
     * @return Task updated as complete.
     */
    protected Task markTask(int index) {
        Task performedTask = tasks.get(index).performTask();
        tasks.set(index, performedTask);
        return performedTask;
    }

    /**
     * Updates a Task at a given index in the collection as incomplete.
     * @return Task updated as incomplete.
     */
    protected Task unmarkTask(int index) {
        Task undidTask = tasks.get(index).undoTask();
        tasks.set(index, undidTask);
        return undidTask;
    }

    /**
     * Adds a new Task to the collection of Tasks set belonging to the user.
     * @param newTask new Task created by user.
     */
    protected void add(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Removes a Task at a given index from the collection of Tasks set belonging to the user.
     * @param newTask Task deleted by the user.
     */
    protected Task delete(int index) {
        return tasks.remove(index);
    }

    protected int getSize() {
        return tasks.size();
    }

    /**
     * Reads the collection of Tasks belonging to the user, and then formats it as a String where
     * each task is separated by a linebreak and labeled numerically.
     * @return String consisting of all Taks belonging to the user.
     */
    protected String enumerateList() {
        ListIterator<Task> it = tasks.listIterator();
        StringBuilder output = new StringBuilder();
        while (it.hasNext()) {
            output.append(it.nextIndex() + 1).append(" ").append(it.next()).append("\n");
        }
        return output.toString();
    }

    protected String find(String term) {
        List<Task> filteredTaskList = tasks.stream()
                .filter(task -> task.getDesc().contains(term))
                .collect(Collectors.toList());
        return new TaskList(filteredTaskList).enumerateList();
    }
}
