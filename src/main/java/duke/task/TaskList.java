package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.exception.InvalidIndexException;

/**
 * Encapsulates the various commands and operations for tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public ArrayList<Task> getList() {
        return this.tasks;
    }
    public int getListSize() {
        return this.tasks.size();
    }

    /**
     * Returns the task located at the given index.
     *
     * @param index The given index for the ArrayList.
     * @return The task located at the index of the ArrayList.
     * @throws InvalidIndexException The exception that occurs when the index given is invalid.
     */
    public Task retrieveTask(int index) {
        try {
            return this.tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("no tasks exist at this index");
        }
    }
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes the task located at the given index.
     *
     * @param index The given index for the ArrayList.
     * @throws InvalidIndexException The exception that occurs when the index given is invalid.
     */
    public void deleteTask(int index) {
        try {
            this.tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("no tasks exist at this index");
        }
    }

    /**
     * Marks the task located at the given index as completed.
     *
     * @param index The given index for the ArrayList.
     * @throws InvalidIndexException The exception that occurs when the index given is invalid.
     */
    public void markTaskDone(int index) {
        try {
            Task selectedTask = tasks.get(index);
            selectedTask.markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("no tasks exist at this index");
        }
    }
    /**
     * Sets the priority of the task located at the given index.
     *
     * @param index The given index for the ArrayList.
     * @param priority The given priority for the task.
     * @throws InvalidIndexException The exception that occurs when the index given is invalid.
     */
    public void setTaskPriority(int index, Priority priority) {
        try {
            Task selectedTask = tasks.get(index);
            selectedTask.setPriority(priority);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("no tasks exist at this index");
        }
    }

    /**
     * Returns the list of tasks due/occurring at a given date.
     *
     * @param time The given date.
     * @return An arraylist containing the tasks that matches the criteria.
     */
    public List<Task> getDueTasks(LocalDate time) {
        return this.tasks.stream().filter(x -> x.isDateEqual(time)).collect(Collectors.toList());
    }
    /**
     * Marks the task located at the given index as uncompleted.
     *
     * @param index The given index for the ArrayList.
     * @throws InvalidIndexException The exception that occurs when the index given is invalid.
     */
    public void markTaskUndone(int index) {
        try {
            Task selectedTask = tasks.get(index);
            selectedTask.markUndone();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("no tasks exist at this index");
        }
    }

    /**
     * Returns the list of tasks that matches the queried keyword.
     *
     * @param queries The given query keywords.
     * @return An arraylist containing the tasks that matches the query.
     */
    public List<Task> findTasks(String ... queries) {
        return this.tasks.stream().filter(x -> x.isQueriesPresent(queries)).collect(Collectors.toList());
    }
}
