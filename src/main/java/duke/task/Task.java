package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles the initialising of Tasks.
 *
 * @author Kang Wei
 */
public class Task {

    /**
     * Determines the type that this task is.
     */
    public enum Type {
        TODO,
        EVENT,
        DEADLINE;
    }

    /**
     * Determines the priority that this task has.
     */
    public enum Priority {
        HIGH,
        MED,
        LOW,
        NONE
    }

    protected String taskDescription;
    protected String miscDescription;
    protected LocalDate taskDate;
    protected boolean hasDateTime = false; // Changed to true if the task is linked to a LocalDateTime dateTime
    protected boolean isDone;

    private Type taskType;
    private Priority priority = Priority.NONE;

    /**
     * Constructor for a TODO task.
     *
     * @param taskDescription The main description for the task.
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
        this.taskType = Type.TODO;
    }

    /**
     * Constructor for a DEADLINE or EVENT task.
     *
     * @param taskDescription The main description for the task.
     * @param miscDescription The miscellaneous description for the task.
     * @param taskType        A Type Enum specifying it the task is a DEADLINE
     *                        or a EVENT.
     */
    public Task(String taskDescription, String miscDescription, Type taskType) {
        this.taskDescription = taskDescription;
        this.taskType = taskType;

        if (taskType.equals(Type.DEADLINE)) {
            /* If miscDescription is of a particular format, then convert it into a LocalDateTime object.
            If not, then the miscDescription is stored as a String. */
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            try {
                LocalDate date = LocalDate.parse(miscDescription, inputFormatter);
                taskDate = date;
                hasDateTime = true;
                this.miscDescription = outputFormatter.format(date);
            } catch (DateTimeParseException e) {
                this.miscDescription = miscDescription;
            }
        } else if (taskType.equals(Type.EVENT)) {
            this.miscDescription = miscDescription;
        }
    }

    /**
     * Sets this tasks's priority to the input priority.
     *
     * @param priority The priority to set to.
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    private String getStatusIcon() {
        return ("[" + (isDone ? "X" : " ") + "]");
    }

    /**
     * Updates the status of the task. If task status
     * is already what it's supposed to be changed to,
     * then no change occurs and this functions returns a reminder.
     *
     * @param changeTo The status that the task should be changed to.
     *                 If true, then isDone is changed to true. If false, then isDone is changed
     *                 to false.
     * @return         Returns a string that describes the change made/not made.
     */
    public String updateStatus(boolean changeTo) {
        if (isDone) {
            if (changeTo) {
                return ("Honeypie, this task has already been marked as done!");
            } else {
                isDone = !isDone;
                return ("This task has been successfully marked as not done yet!");
            }
        } else {
            if (changeTo) {
                isDone = !isDone;
                return ("This task has been successfully marked as done!");
            } else {
                return ("Honeypie, this task has already been marked as not done yet!");
            }
        }
    }

    @Override
    public String toString() {
        switch (taskType) {
        case TODO:
            return "[T]" + getStatusIcon() + " " + taskDescription + " || Priority: " + priority;
        case DEADLINE:
            return "[D]" + getStatusIcon() + " " + taskDescription + " (by: " + miscDescription + ")" + " || Priority: " + priority;
        case EVENT:
            return "[E]" + getStatusIcon() + " " + taskDescription + " (at: " + miscDescription + ")" + " || Priority: " + priority;
        default:
            return "";
        }
    }
}
