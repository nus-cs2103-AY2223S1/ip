package duke.data;

import java.io.Serializable;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import duke.task.Task;

/**
 * Stores and retrieves the Task objects.
 */
public class TaskList implements Serializable {

    /** Linked list to store the user's tasks */
    private LinkedList<Task> tasks;


    /**
     * Creates a new TaskList object.
     */
    public TaskList() {
        this.tasks = new LinkedList<>();
    }

    private TaskList(LinkedList<Task> tasks) {
        this.tasks = tasks;
    }




    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int indexNumber) {
        return this.tasks.get(indexNumber);
    }


    /**
     * Sets a Task object at the specified index.
     *
     * @param indexNumber Index number to set the Task object.
     * @param t Task object to set.
     */
    public void setTask(int indexNumber, Task t) {
        this.tasks.set(indexNumber, t);
    }


    /**
     * Adds a Task object to the end of the list of tasks.
     *
     * @param t Task object to add.
     */
    public void addTask(Task t) {
        this.tasks.addLast(t);
    }


    /**
     * Removes and returns the Task object at the specified index.
     *
     * @param indexNumber Index number of Task object to remove.
     * @return Task object that was removed.
     */
    public Task removeTask(int indexNumber) {
        Task t = this.tasks.remove(indexNumber);
        return t;
    }


    /**
     * Returns all Task objects which contain the specified keyword in the description.
     *
     * @param keyword Keyword to search for in the description.
     * @return TaskList containing the Task objects.
     *
     */
    public TaskList searchTasks(String keyword) {

        Collector<Task, ?, LinkedList<Task>> c = Collectors.toCollection(() -> (new LinkedList<Task>()));

        // Stream the Task objects, filter the Tasks that contain the keyword
        LinkedList<Task> results = this.tasks.stream()
            // Filter the Tasks that contain the keyword
            .filter((t) -> t.getDescription().contains(keyword))
            // Collect the results back into a linked list
            .collect(c);


        return new TaskList(results);
    }


    /**
     * Sorts the Task objects based on the specified comparator.
     *
     * @param comp Comparator to sort by.
     * @return A sorted copy of the Task objects.
     */
    public TaskList sortTasks(Comparator<Task> comp) {
        LinkedList<Task> copy = new LinkedList<>(this.tasks);

        copy.sort(comp);

        return new TaskList(copy);
    }


    /**
     * Returns the string representation of the TaskList object.
     *
     * @return String representation of the TaskList object.
     */
    @Override
    public String toString() {
        return String.format("%s", this.tasks.toString());
    }

}
