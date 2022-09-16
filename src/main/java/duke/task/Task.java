package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Parent class for Deadline, Event and Todo.
 */
public class Task {
    private final String taskName;
    private final boolean isMarked;
    private final LocalDate date;
    private final LocalTime time;

    /**
     * Constructs a Task instance whereas a new Task is first initialised.
     *
     * @param taskName description of the Task.
     * @param date date in LocalDate format.
     * @param time time in LocalTime format.
     */
    public Task(String taskName, LocalDate date, LocalTime time) {
        this.taskName = taskName;
        this.isMarked = false;
        this.date = date;
        this.time = time;
    }

    /**
     * Constructs a Task instance to replace the old Task when it is marked/ unmarked.
     *
     * @param taskName description of the Task.
     * @param isMarked status of the Task to determine whether it is done.
     * @param date date in LocalDate format.
     * @param time time in LocalTime format.
     */
    public Task(String taskName, boolean isMarked, LocalDate date, LocalTime time) {
        this.taskName = taskName;
        this.isMarked = isMarked;
        this.date = date;
        this.time = time;
    }

    /**
     * Returns new Task as marked.
     *
     * @return marked Task.
     */
    public Task mark() {
        return new Task(this.taskName, true, this.date, this.time);
    }

    /**
     * Return new Task as unmarked.
     *
     * @return unmarked Task.
     */
    public Task unmark() {
        return new Task(this.taskName, false, this.date, this.time);
    }

    /**
     * Return the status of Task to check whether the Task is finished.
     *
     * @return true if and only if the Task is ticked as done.
     */
    public boolean isMarked() {
        return this.isMarked;
    }

    /**
     * Returns the description of the Task.
     *
     * @return the taskName in String.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns Task's date and time in a better format for visualisation.
     *
     * @return formatted date and time in String.
     */
    public String getOutputDateAndTime() {
        if (this.time != null && this.date != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            return this.date.format(formatter) + ", " + this.time + ")";
        } else {
            return "";
        }
    }

    private String getToStringDateAndTime() {
        if (this.time != null && this.date != null) {
            return "," + this.getDate() + "," + this.getTime();
        } else {
            return "";
        }
    }

    /**
     * Gets and returns Task's Date.
     *
     * @return Date in LocalDate.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Gets and returns Task's Time.
     *
     * @return Time in LocalTime.
     */
    public LocalTime getTime() {
        return this.time;
    }

    /**
     * Returns the different type of Tasks.
     * @return "D" if Deadline, "T" if Todo, "E" if Event.
     */
    public String getTaskType() {
        return "PARENT TYPE (GOT BUG)";
    }

    @Override
    public String toString() {
        //return "[" + (this.isMarked() ? "X" : " ") + "]" + this.taskName + this.getDateAndTime() + "\n";
        return (this.isMarked() ? "1" : "0") + "," + this.taskName + this.getToStringDateAndTime() + "\n";
    }
}
