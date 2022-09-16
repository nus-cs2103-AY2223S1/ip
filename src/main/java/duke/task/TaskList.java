package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Public class for a TaskList, which is a list of tasks
 *
 * @author kaij77
 * @version 0.1
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Public constructor for a TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Public constructor for a TaskList.
     *
     * @param data
     */
    public TaskList(List<String> data) {
        this.tasks = new ArrayList<>();
        for (String entry : data) {
            this.tasks.add(Task.parse(entry));
        }
    }

    /**
     * Calculates the size of the TaskList.
     *
     * @return The size of the TaskList
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Marks the Task at the given index in the TaskList as done.
     *
     * @param index The index of the Task to be marked
     * @return The String representation of the marked Task
     */
    public String markTask(int index) {
        return this.tasks.get(index - 1).mark();
    }

    /**
     * Unmarks the Task at the given index in the TaskList.
     *
     * @param index The index of the Task to be unmarked
     * @return The String representation of the unmarked Task
     */
    public String unmarkTask(int index) {
        return this.tasks.get(index - 1).unmark();
    }

    /**
     * Deletes the Task with the given index from the TaskList.
     *
     * @param index  The index of the Task to be deleted
     * @return The String representation of the deleted Task
     */
    public String deleteTask(int index) {
        Task task = this.tasks.remove(index - 1);
        return task.toString();
    }

    /**
     * Creates a new ToDo and adds it to the TaskList.
     *
     * @param description The description of the ToDo
     * @return The String representation of the newly added ToDo
     */
    public String addToDo(String description) {
        ToDo task = new ToDo(description);
        this.addToTasks(task);
        return task.toString();
    }

    /**
     * Creates a new Deadline and adds it to the TaskList.
     *
     * @param description The description of the Deadline
     * @param by The Date on which the Deadline is due
     * @return The String representation of the newly added Deadline
     */
    public String addDeadline(String description, LocalDate by) {
        Deadline task = new Deadline(description, by);
        this.addToTasks(task);
        return task.toString();
    }

    /**
     * Creates a new Event and adds it to the TaskList.
     *
     * @param description The description of the Event
     * @param at The date and time of the Event
     * @return The String representation of the newly added Event
     */
    public String addEvent(String description, LocalDate at) {
        Event task = new Event(description, at);
        this.addToTasks(task);
        return task.toString();
    }

    /**
     * Adds the given Task to the TaskList.
     *
     * @param task The Task to be added
     */
    private void addToTasks(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns the String representation of the TaskList.
     *
     * @return The String representation of the TaskList
     */
    @Override
    public String toString() {
        String result = "";
        if (this.tasks.size() == 0) {
            return null;
        } else {
            for (int i = 0; i < this.tasks.size(); i++) {
                result += String.format("%d. %s\n", 1 + i, this.tasks.get(i).toString());
            }
        }
        return result;
    }

}