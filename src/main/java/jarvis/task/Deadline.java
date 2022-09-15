package jarvis.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime by;
    public Deadline(String input, boolean isDone) throws DateTimeParseException {
        super(isDone);
        String[] strArr = input.split("/by");
        this.description = strArr[0].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            this.by = LocalDateTime.parse(strArr[1].trim(), formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Wrong date format");
            throw e;
        }
    }

    public Deadline(String input, String by, boolean isDone) {
        super(isDone);
        this.description = input;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            this.by = LocalDateTime.parse(by);
        } catch (DateTimeParseException e) {
            System.out.println("Wrong date format");
            throw e;
        }
    }

    @Override
    public Task.TaskType getTaskType() {
        return Task.TaskType.Deadline;
    }

    @Override
    public String toDataForm() {
        String done = this.isDone ? "1" : "0";
        return "D|" + done + "|" + this.description + "|" + this.by + "\n";
    }

    @Override
    public String toString() {
        String date = this.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        String head = "[D][" + this.getStatusIcon() + "] ";
        String body = this.description + " (by: " + date + ")";
        return head + body;
    }
}
