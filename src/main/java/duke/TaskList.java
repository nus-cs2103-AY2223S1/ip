package duke;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor for the TaskList class.
     *
     * @param tasks An ArrayList object containing Task objects.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Another constructor for the TaskList class.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Returns the ArrayList object that contains Task objects.
     *
     * @return The instance field 'tasks' that is an ArrayList object.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns the task in the ArrayList that has the given index position.
     *
     * @param index The index position of the task in the ArrayList.
     * @return The task in the ArrayList that has the given index position.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Removes a task from the ArrayList object 'tasks'.
     *
     * @param taskNumber An integer that provides the index of the task in the ArrayList to be removed.
     */
    public void deleteTask(int taskNumber) {
        assert taskNumber >= 0 : "taskNumber should not be negative";

        Task t = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        System.out.println("     Ok! I have removed the following task!:\n"
                + "       " + t.toString() + "\n"
                + "     You now have a total of " + tasks.size() + " tasks!");
    }

    /**
     * Overloaded method for deleteTask(), meant for JavaFX.
     *
     * @param taskNumber The position of the task, relative to other tasks, starting count from 1.
     * @param dialogContainer The VBox object that contains the chat messages and images.
     * @param dukeImage The image of Duke.
     */
    public void deleteTask(int taskNumber, VBox dialogContainer, Image dukeImage) {
        assert taskNumber >= 0 : "taskNumber should not be negative";

        Task t = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        String taskDeleted = "Ok! I have removed the following task!:\n"
                + "       " + t.toString() + "\n"
                + "You now have a total of " + tasks.size() + " tasks!";
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(taskDeleted, dukeImage));
    }

    /**
     * Adds a task to the ArrayList object 'tasks'.
     *
     * @param t The Task object to be added into the ArrayList object.
     */
    public void addTask(Task t) {
        assert t != null : "Task to be added should not be null";

        tasks.add(t);
        System.out.println("     Ok! I have added the following "
                + ((t instanceof Todo)
                        ? "Todo "
                        : (t instanceof Event)
                        ? "Event "
                        : "Deadline ")
                + "task!:\n"
                + "       " + t.toString() + "\n"
                + "     You now have a total of " + tasks.size() + " tasks!");
    }

    /**
     * Overloaded method for addTask(), meant for JavaFX.
     *
     * @param t The task to be added.
     * @param dialogContainer The VBox object that contains the chat messages and images.
     * @param dukeImage The image of Duke.
     */
    public void addTask(Task t, VBox dialogContainer, Image dukeImage) {
        assert t != null : "Task to be added should not be null";

        tasks.add(t);
        String taskAdded = "Ok! I have added the following "
                + ((t instanceof Todo)
                ? "Todo "
                : (t instanceof Event)
                ? "Event "
                : "Deadline ")
                + "task!:\n"
                + "       " + t.toString() + "\n"
                + "You now have a total of " + tasks.size() + " tasks!";
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(taskAdded, dukeImage));
    }

    public static void main(String[] args) {
        TaskList tl = new TaskList();
        Task t = new Todo("read books");
        tl.addTask((t));
        tl.addTask(new Deadline("return books", "2022-10-28 18:00"));
        tl.addTask(new Event("attend wedding", "2022-12-20"));
    }
}
