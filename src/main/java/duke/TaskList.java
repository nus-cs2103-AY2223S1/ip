package duke;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * A class that encapsulates the TaskList object
 * which contains the task list e.g., it has operations to add/delete tasks in the list
 * @author  Wee Xin Yang, Markus
 * @version 0.1
 * @since   2022-8-24
 */
public class TaskList {
    private ArrayList<Task> arrayList;

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

    public void forEach(Consumer<? super Task> consumer) {
        arrayList.forEach(consumer);
    }


}
