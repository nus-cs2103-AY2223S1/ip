package blink;

import java.time.LocalDate;
import java.util.ArrayList;

import blink.task.Deadlines;
import blink.task.Events;
import blink.task.Task;
import blink.task.ToDos;

/**
 * TaskList contains all the task inputted into the current run of
 * Blink program.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor to create empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor to create TaskList with all the tasks inside
     * arraylist passed.
     *
     * @param tasks ArrayList of task to store
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Delete task from TaskList at specified position and display
     * information of deleted task.
     *
     * @param pos Number position of task to delete
     * @return Task that is deleted, with all its information before
     *     it was deleted
     */
    public Task deleteTask(int pos) {
        int minIndex = 1;
        if (pos < minIndex || pos > this.length()) {
            throw new BlinkException("Invalid number input");
        }
        Task temp = this.tasks.get(pos - 1);
        this.tasks.remove(pos - 1);
        return temp;
    }

    /**
     * Number of tasks currently within TaskList.
     *
     * @return Number of task in TaskList
     */
    public int length() {
        int len = this.tasks.size();
        assert len >= 0; //length cannot be lesser than 0
        return len;
    }

    /**
     * Get a task at a specified position in the TaskList.
     *
     * @param pos Number position of task to get
     * @return Task at position specified
     */

    public Task getTask(int pos) {
        assert pos >= 0;
        return this.tasks.get(pos);
    }

    /**
     * String showing number of task currently in TaskList then displays all the task
     * in order of which was inputted first.
     *
     * @return String representation of all tasks within the TaskList
     */
    public String listTask() {
        String display = "";
        if (this.length() == 0) {
            return "No task currently";
        } else if (this.length() == 1) {
            display = display + "There is only 1 task currently:\n";
        } else {
            display = "There is a total of " + this.length() + " tasks currently:";
        }
        for (int x = 0; x < this.length(); x++) {
            display = display + "\n" + (x + 1) + ": " + this.getTask(x);
        }
        return display;
    }

    /**
     * Get task at specified position marked.
     *
     * @param num Number position of task to mark
     */
    public void mark(int num) {
        int minIndex = 1;
        if (num < minIndex || num > this.length()) {
            throw new BlinkException("Invalid number input");
        }
        this.tasks.get(num - 1).mark();
    }

    /**
     * Get task at specified position unmarked.
     *
     * @param num Number position of task to unmark
     */
    public void unMark(int num) {
        int minIndex = 1;
        if (num < minIndex || num > this.length()) {
            throw new BlinkException("Invalid number input");
        }
        this.tasks.get(num - 1).unMark();
    }

    /**
     * String to show number of remaining task after deletion of a task.
     *
     * @return String containing number of remaining task in TaskList
     */
    public String toStringAfterDelete() {
        if (this.length() == 0) {
            return "No tasks remaining";
        } else if (this.length() == 1) {
            return "1 task remains";
        } else {
            return this.length() + " tasks remaining";
        }
    }

    /**
     * Gets all the task in TaskList with the same date.
     *
     * @param date Date that tasks check equality with
     * @return ArrayList containing all tasks with same date
     *     as the one specified
     */
    public ArrayList<Task> filter(LocalDate date) {
        ArrayList<Task> sameDates = new ArrayList<>();
        for (int x = 0; x < this.length(); x++) {
            Task temp = this.getTask(x);
            if (temp.checkDate(date)) {
                sameDates.add(temp);
            }
        }
        return sameDates;
    }

    /**
     * Add Events object into TaskList.
     *
     * @param desc Description of Events object
     * @param date Date of Events object
     * @return Event object that is just added to TaskList
     *     and its information
     */
    public Events addEvent(String desc, String date) {
        Events temp = new Events(desc.strip(), date.strip());
        this.tasks.add(temp);
        return temp;
    }

    /**
     * Add Deadline object into TaskList.
     *
     * @param desc Description of Deadline object
     * @param date Date of Deadline object
     * @return Deadline object that is just added to TaskList
     *     and its information
     */
    public Deadlines addDeadline(String desc, String date) {
        Deadlines temp = new Deadlines(desc.strip(), date.strip());
        this.tasks.add(temp);
        return temp;
    }

    /**
     * Add ToDos object into TaskList.
     *
     * @param desc Description of ToDos object
     * @return ToDos object that is just added to TaskList
     *     and its information
     */
    public ToDos addTodo(String desc) {
        ToDos temp = new ToDos(desc.strip());
        this.tasks.add(temp);
        return temp;
    }

    /**
     * Searches for all Tasks inside TaskList that contains the keyword specified.
     *
     * @param keyword Keyword to search for in all Tasks in TaskList
     * @return ArrayList of Tasks that contain the keyword
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> temp = new ArrayList<>();
        for (int x = 0; x < this.length(); x++) {
            Task task = this.getTask(x);
            if (task.hasKeyword(keyword)) {
                temp.add(task);
            }
        }
        return temp;
    }

    /**
     * Tags the task inside TaskList with the specified input
     *
     * @param index Position of task to tag in TaskList
     * @param tag String input for the tag content
     */
    public void tagTask(int index, String tag) {
        int minIndex = 1;
        if (index < minIndex || index > this.length()) {
            throw new BlinkException("Invalid number input");
        }
        Task task = this.getTask(index - 1);
        task.addTag(tag);
    }
}
