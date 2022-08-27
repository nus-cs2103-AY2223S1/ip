package DataStruct;

import DaveExceptions.DaveNoTasksException;
import Task.Task;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {

    ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to the tasklist.
     *
     * @param task task to be added to the tasklist
     * @return String representation of the addition action.
     */
    public String add(Task task) {
        tasks.add(task);
        return String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                task, tasks.size());
    }

    /**
     * Removes a task from the tasklist.
     *
     * @param index index of task to be removed from the tasklist
     * @return String representation of the removal action.
     */
    public String remove(int index) throws IndexOutOfBoundsException, DaveNoTasksException {
        if (tasks.size() == 0) {
            throw new DaveNoTasksException();
        } else {
            Task task = tasks.remove(index - 1);
            return String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.",
                    task, tasks.size());
        }
    }

    /**
     * Retrieves a task from the tasklist.
     *
     * @param index index of task to be retrieved from the tasklist
     * @return String representation of the retrieval action.
     */
    public Task get(int index) throws ArrayIndexOutOfBoundsException, DaveNoTasksException {
        if (tasks.size() == 0) {
            throw new DaveNoTasksException();
        } else {
            return tasks.get(index);
        }
    }

    public String findTasks(String keyword) {
        StringBuilder result = new StringBuilder("Here are the matching tasks in your list: \n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.contains(keyword)) {
                result.append(String.format("%d. %s \n", i + 1, task));
            }
        }
        return result.toString();
    }

    /**
     * Gets the lenght of the the tasklist as an int.
     *
     * @return Lenght of the tasklist as an int.
     */
    public int size() {
        return tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            result.append(String.format("%d. %s \n", i + 1, tasks.get(i)));
        }
        return result.toString();
    }

}
