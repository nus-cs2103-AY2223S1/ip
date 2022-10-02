package cwq.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class encapsulates Duke deadlines
 */
public class Deadline extends Task {
    private final LocalDateTime ddlTime;

    /**
     * Constructor for Deadline class
     * @param taskDescription the content of the task
     * @param ddlTime the deadline time
     */
    public Deadline(String taskDescription, String ddlTime) {
        super(taskDescription);
        this.ddlTime = LocalDateTime.parse(ddlTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public LocalDateTime getTime() {
        return ddlTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + "(by: " + ddlTime.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
    }

}
