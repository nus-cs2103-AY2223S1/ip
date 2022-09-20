package duke;

import task.Task;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * A class that encapsulates the TaskList object
 * which contains the task list e.g., it has operations to add/delete tasks in the list
 *
 * @author Wee Xin Yang, Markus
 * @version 0.1
 * @since 2022-8-24
 */
public class TaskList {
    private final ArrayList<Task> arrayList;

    /**
     * Constructor for TaskList Object
     */
    public TaskList() {

        this.arrayList = new ArrayList<>(100);
    }

    /**
     * Adds a Task object to the encapsulated ArrayList
     *
     * @param task The Task object to be added
     */
    public void add(Task task) {
        this.arrayList.add(task);
    }

    public String list() {
        int counter = 1;
        String list = "";
        for (Task task : arrayList) {
            list += counter + ". " + task.toString() + "\n";
            counter++;
        }

        return list;
    }

    /**
     * Returns the task indexed at the inputted value in the Arraylist
     *
     * @param index the int index of the Task object to be removed
     * @return the Task in that index of the ArrayList
     */
    public Task get(int index) {
        return this.arrayList.get(index);

    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index index of the element to replace
     * @param task  Task to be stored at the specified position
     * @return the Task previously at the specified position
     */
    public Task set(int index, Task task) {
        return this.arrayList.set(index, task);
    }

    /**
     * Removes a Task object to the encapsulated ArrayList
     *
     * @param index the int index of the Task object to be removed
     */
    public Task remove(int index) {
        return this.arrayList.remove(index);
    }

    /**
     * Returns an int representing the number of Tasks in the ArrayList
     *
     * @return The length of the ArrayList
     */
    public int size() {

        return this.arrayList.size();
    }

    /**
     * Performs the given action for each element of the Iterable
     * until all elements have been processed or the action throws an exception.
     */
    public void forEach(Consumer<? super Task> consumer) {

        arrayList.forEach(consumer);
    }


}
