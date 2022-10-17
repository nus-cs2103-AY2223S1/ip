package duke.tasks;

import duke.functions.Ui;
import duke.support.DukeException;

/**
 * Task class for tasks that users input into the Duke bot.
 *
 * @author lauralee
 */
public class Task {

    private static int numberTasks = 0;
    private String name;
    private String type;

    // Status of a task. The status of the task will be 0 when the task is unamrked and 1 when it is marked.
    private int status;

    /**
     * Constructor for the Task class.
     *
     * @param name Description of the Task being added.
     * @param type Type of the Task being added, i.e. T, E or D.
     */
    public Task(String name, String type) {
        this.name = name;
        this.type = type;
        this.status = 0;
        numberTasks++;
        assert(numberTasks <= 100);
    }

    /**
     * Overloaded constructor for Task class.
     *
     * @param name Description of the Task being added.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Marks a Task.
     */
    public String mark() {
        this.status = 1;
        return Ui.printMark(this.type, this.name);
    }

    /**
     * Unmarks a Task.
     */
    public String unMark() {
        this.status = 0;
        return Ui.printUnmark(this.type, this.name);
    }

    /**
     * Indicates the numbers of Tasks that has been added by the user.
     *
     * @return The number of Tasks that has been added by the user.
     */
    public static int getNumberTasks() {
        return numberTasks;
    }

    /**
     * Decrements the number of tasks by 1 when a task is deleted from the list.
     */
    public static void deleteTask() {
        numberTasks--;
    }

    /**
     * Snoozes task by allowing the user to postpone the deadline of the task.
     *
     * @param time The new deadline of the task.
     */
    public String snoozeTask(String time) throws DukeException.SnoozeException {
        if (this instanceof Event) {
            //Safe to typecast this to Event as this block will only run if this is an Event instance.
            @SuppressWarnings("unchecked")
            Event snoozeEvent = (Event) this;
            snoozeEvent.snoozeDeadline(time);
            return Ui.printSnoozeEvent(this);
        } else if (this instanceof Deadline) {
            //Safe to typecast this to Deadline as this block will only run if this is a Deadline instance.
            @SuppressWarnings("unchecked")
            Deadline snoozeDeadline = (Deadline) this;
            snoozeDeadline.snoozeDeadline(time);
            return Ui.printSnoozeDeadline(this);
        } else {
            throw new DukeException.SnoozeException();
        }
    }

    /**
     * The description of the Task added including its type, name and when it needs to be completed by.
     *
     * @return The description of a Task.
     */
    public String output() {
        if (this.status == 0) {
            return "[" + this.type + "][ ] " + this.name;
        } else {
            return "[" + this.type + "][X] " + this.name;
        }
    }

}

