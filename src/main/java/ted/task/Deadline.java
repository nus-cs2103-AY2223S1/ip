package ted.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents task with a deadline. A <code>Deadline</code> object corresponds to a task description
 * and task deadline given by the user.
 */
public class Deadline extends Task {
    private LocalDateTime taskDeadline;

    /**
     * Creates Deadline object with default not done status.
     *
     * @param taskDescription task name.
     * @param taskDeadline task deadline.
     */
    public Deadline(String taskDescription, String taskDeadline) {
        super(taskDescription);
        this.taskDeadline = LocalDateTime.parse(taskDeadline.replace(' ', 'T'));
    }

    /**
     * Creates Deadline object that is specified to be done or not done.
     *
     * @param taskDescription task name.
     * @param isTaskDone boolean indicating task's done status.
     * @param taskDeadline task deadline.
     */
    public Deadline(String taskDescription, boolean isTaskDone, String taskDeadline) {
        super(taskDescription, isTaskDone);
        this.taskDeadline = LocalDateTime.parse(taskDeadline.replace(' ', 'T'));
    }

    /**
     * Returns Deadline in the correct String format for bot response.
     *
     * @return String that represents Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.taskDeadline.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mm a")) + ")";
    }

    /**
     * Returns Deadline in the correct String format to write to file.
     *
     * @return String that represents Deadline.
     */
    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + this.taskDeadline + "\n";
    }

    /**
     * Indicates if a Deadline object has a deadline after current time.
     *
     * @return boolean that indicates whether user should be reminded of the particular Deadline object.
     */
    @Override
    public boolean shouldRemind() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return taskDeadline.isAfter(currentDateTime);
    }
}
