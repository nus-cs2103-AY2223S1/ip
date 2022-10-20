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
        assert index >= 0 : "Index cannot be negative";
        Task task = this.tasks.remove(index - 1);
        return task.toString();
    }

    /**
     * Creates a new ToDo and adds it to the TaskList.
     *
     * @param description The description of the ToDo
     * @return The String representation of the newly added ToDo
     */
    public String addToDo(String description, String note) {
        ToDo task = new ToDo(description, note);
        this.addToTasks(task);
        return task.toString();
    }

    /**
     * Creates a new Deadline and adds it to the TaskList.
     *
     * @param description The description of the Deadline
     * @param by          The Date on which the Deadline is due
     * @param note
     * @return The String representation of the newly added Deadline
     */
    public String addDeadline(String description, LocalDate by, String note) {
        Deadline task = new Deadline(description, by, note);
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
    public String addEvent(String description, LocalDate at, String note) {
        Event task = new Event(description, at, note);
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
     * Searches for and returns a List of Tasks containing the keyword.
     *
     * @param keyword The keyword
     * @return List of Tasks containing the keyword
     */
    public TaskList searchTasks (String keyword) {
        TaskList result = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            if (curr.match(keyword)) {
                result.addToTasks(curr);
            }
        }
        return result;
    }

    public void editNote(int index, String newNote) {
        tasks.get(index).editNote(newNote);
    }

    public void deleteNote(int index) {
        tasks.get(index).deleteNote();
    }

    /**
     * Returns the String representation of the TaskList.
     *
     * @return The String representation of the TaskList
     */
    @Override
    public String toString() {
        // Solution below adapted from https://github.com/dexter-sim/ip
        String result = "";
        if (this.tasks.size() == 0) {
            return null;
        } else {
            assert this.tasks.size() > 0 : "There should be at least 1 Task in the list.";
            for (int i = 0; i < this.tasks.size(); i++) {
                result += String.format("%d. %s\n", 1 + i, this.tasks.get(i).toString());
            }
        }
        return result;
    }

}