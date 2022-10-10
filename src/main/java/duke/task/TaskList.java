package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Class for TaskList components of Duke.
 */
public class TaskList {

    protected ArrayList<Task> list;
    private int pointer = 0;
    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>(100);
    }

    /**
     * Constructor for TaskList.
     * @param taskList - tasklist to copy data from
     */
    public TaskList(TaskList taskList) {
        this.list = taskList.list;
    }

    /**
     * Adds a task to the list.
     * @param description - description of the task
     */
    public Task addTask(String description) {
        Task val = new Task(description);
        this.list.add(val);
        pointer++;
        return val;
    }

    /**
     * Adds a deadline to the list.
     * @param desc - description of the task
     * @param deadline - the date of the deadline
     */
    public Deadline addDeadline(String desc, String deadline) {
        Deadline val = new Deadline(desc, deadline);
        this.list.add(val);
        pointer++;
        return val;
    }

    /**
     * Adds a deadline to the list using a date.
     * @param desc - description of the task
     * @param date - the date of the deadline in LocalDate format
     */
    public Deadline addDeadline(String desc, LocalDate date) {
        Deadline val = new Deadline(desc, date);
        this.list.add(val);
        pointer++;
        return val;
    }

    /**
     * Adds an event to the list.
     * @param desc - description of the task
     * @param time - the time of the event
     */
    public Event addEvent(String desc, String time) {
        Event val = new Event(desc, time);
        this.list.add(val);
        pointer++;
        return val;
    }

    /**
     * Returns the task containing the specific keyword.
     * @param keyword - the keyword to search for
     * @return - a resultant TaskList containing the tasks containing the keyword
     */
    public TaskList find(String keyword) {
        TaskList result = new TaskList();
        for (Task task : this.list) {
            if (task.getDescription().contains(keyword)) {
                result.list.add(task);
            }
        }
        return result;
    }

    public int getLength() {
        return this.list.size();
    }

    public Task get(int index) {
        return this.list.get(index);
    }

    public void delete(int index) {
        this.list.remove(index);
    }

    /**
     * Marks a task as done.
     * @param index - index of the task to mark as done in the TaskList.
     */
    public void mark(int index) {
        this.get(index).mark();
    }

    /**
     * Marks a task as undone.
     * @param index - index of the task to mark as undone in the TaskList.
     */
    public void unmark(int index) {
        this.get(index).unmark();
    }

    /**
     * Returns a string representation of the TaskList for saving into a file.
     * @return - string representation of the TaskList for saving into a file.
     */
    public String saveData() {
        String result = "";
        for (Task task : this.list) {
            result += String.format("%s", task.getSavedString());
        }
        return result;
    }

    /**
     * Prints the TaskList in a readable format.
     */
    public void printData() {
        this.list.forEach(x -> System.out.println(String.format("%s. %s", this.list.indexOf(x) + 1, x)));
    }

    public String getData() {
        String result = "";
        for (Task task : this.list) {
            result += String.format("%s. %s\n", this.list.indexOf(task) + 1, task);
        }
        return result;
    }
}
