package duke;

import java.util.ArrayList;

/**
 * Handles the list and list edit functions.
 *
 * @author Yuvaraj Kumaresan
 */
public class TaskList {

    /**
     * The array used to store the tasks.
     */
    protected ArrayList<Task> arrayList;

    /**
     * Constructor.
     *
     * @param list The arraylist to modify.
     */
    public TaskList(ArrayList<Task> list) {
        this.arrayList = list;
    }

    /**
     * Handles adding a todo task to the list.
     *
     * @param task The todo task to add to the list.
     */
    public void addTodo(ToDo task) {
        this.arrayList.add(task);
    }

    /**
     * Handles adding a deadline task to the list.
     *
     * @param task The deadline task to add to the list.
     */
    public void addDeadline(Deadline task) {
        this.arrayList.add(task);
    }

    /**
     * Handles adding a Duration task to the list.
     *
     * @param task The duration task to be added to the list.
     */
    public void addDuration(Duration task) {
        this.arrayList.add(task);
    }

    /**
     * Handles adding an event task to the list.
     *
     * @param task The event task to add to the list.
     */
    public void addEvent(Event task) {
        this.arrayList.add(task);
    }

    /**
     * Handles deleting a task from the list.
     *
     * @param taskNumber The task number to delete from the list.
     */
    public void delete(int taskNumber) {
        this.arrayList.remove(taskNumber);
    }

    /**
     * Handles marking a task.
     *
     * @param toBeMarked The task to be marked.
     */
    public void mark(Task toBeMarked) {
        toBeMarked.setIsDone(true);
    }

    /**
     * Handles unmarking a task.
     *
     * @param toBeUnmarked The task to be unmarked.
     */
    public void unmark(Task toBeUnmarked) {
        toBeUnmarked.setIsDone(false);
    }
}
