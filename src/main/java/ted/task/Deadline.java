package ted.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime taskDeadline;

    public Deadline(String taskDescription, String taskDeadline) {
        super(taskDescription);
        this.taskDeadline = LocalDateTime.parse(taskDeadline.replace(' ', 'T'));
    }

    public Deadline(String taskDescription, boolean isTaskDone, String taskDeadline) {
        super(taskDescription, isTaskDone);
        this.taskDeadline = LocalDateTime.parse(taskDeadline.replace(' ', 'T'));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.taskDeadline.format(DateTimeFormatter.ofPattern("d MMM yyyy h:mm a")) + ")";
    }

    public String toFileString() {
        return "D | " + super.toFileString() + " | " + this.taskDeadline + "\n";
    }
}
