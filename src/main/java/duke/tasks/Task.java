package duke.tasks;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    private String description;
    private Boolean isDone;
    private LocalDateTime taskTime;

    /**
     * Standard constructor that defines a Task
     * @param description Description for the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        // Set default taskTime to the start of the epoch
        this.taskTime = LocalDateTime.parse("01-01-1970 00:00", formatter);

    }

    /**
     * Overloaded constructor to create tasks that have set deadlines
     * @param description The string description of the task
     * @param taskTime The time that the task needs to be completed by
     */
    public Task(String description, String taskTime) {
        this.description = description;
        this.isDone = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.taskTime = LocalDateTime.parse(taskTime, formatter);
    }

    /**
     * Overloaded constructor to create tasks that have set deadlines, and are pre-completed
     * @param description The string description of the task
     * @param isDone Marks whether the task has been completed or not
     * @param taskTime The time that the task needs to be completed by
     */
    public Task(String description, boolean isDone, String taskTime) {
        this.description = description;
        this.isDone = isDone;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        this.taskTime = LocalDateTime.parse(taskTime, formatter);
    }


    /**
     * Overloaded constructor to allow for creation of pre-completed tasks.
     * @param description Description for the task
     * @param isDone Marks whether task has been completed
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        // Set default taskTime to the start of the epoch
        this.taskTime = LocalDateTime.parse("01-01-1970 00:00", formatter);
    }

    /**
     * Getter method to return the task's required time of completion
     * @return task's required time of completion
     */
    public LocalDateTime getTaskTime() {
        return this.taskTime;
    }

    /**
     * Checks if the current task's deadline is within 3 days of today
     * @return boolean flag representing whether the above clause is true
     */
    public boolean isWithinDeadline() {
        LocalDateTime now = LocalDateTime.now();
        Period period = Period.between(now.toLocalDate(), this.taskTime.toLocalDate());
        int numDays = period.getDays();
        int numMonths = period.getMonths();
        int numYears = period.getYears();

        return numYears == 0 && numMonths == 0 && numDays <= 3 && numDays >= 0;

    }

    public String getStatusIcon() {
        return isDone? "X" : " ";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getSaveString() {
        return this.isDone + ",," + this.description + ",,";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
