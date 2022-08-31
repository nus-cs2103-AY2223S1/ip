package ava.processor;

import java.util.ArrayList;
import java.util.function.Function;

import ava.task.Task;

/**
 * Class to represent the tasks lists.
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * The constructor for TaskList with no parameters.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * The constructor for TaskList with an input of ArrayList.
     *
     * @param tasks An ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a specific task to the TaskList.
     *
     * @param t A specific task to add.
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Deletes a specific task from the TaskList.
     *
     * @param i Specific task's index number.
     * @return Task taskDeleted.
     */
    public Task delete(int i) {
        return tasks.remove(i);
    }

    /**
     * Marks a specific task of the TaskList.
     *
     * @param i Specific task's index number.
     */
    public void markDone(int i) {
        tasks.get(i).markDone();
    }

    /**
     * Marks a specific task of the TaskList.
     *
     * @param i Specific task's index number.
     */
    public void markUndone(int i) {
        tasks.get(i).markUndone();
    }

    /**
     * Returns the number of tasks inside the TaskList.
     *
     * @return int size of TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns a specific task from the TaskList.
     *
     * @param i Specific task's index number.
     * @return Task object.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Returns the current ArrayList.
     *
     * @return Arraylist.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns a TaskList containing Task that satisfy the supplied predicate.
     *
     * @param predicate The predicate to test the Task with.
     * @return A TaskList containing Task that satisfies the given predicate.
     */
    public TaskList filter(Function<Task, Boolean> predicate) {
        ArrayList<Task> res = new ArrayList<>();
        for (int i = 0; i < this.size(); i++) {
            if (predicate.apply(tasks.get(i))) {
                res.add(tasks.get(i));
            }
        }
        return new TaskList(res);
    }

    /**
     * Returns an array of string representation of the Tasks in the list, numbered from 1.
     *
     * @return An array of string representation of the Tasks in the list, numbered from 1.
     */
    public String[] toStringArray() {
        String[] copy = tasks.stream().map(Object::toString).toArray(String[]::new);
        for (int i = 0; i < size(); i++) {
            copy[i] = (i + 1) + ". " + copy[i];
        }
        return copy;
    }
}
