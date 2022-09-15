package jarvis.task;

import jarvis.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private LocalDateTime at;
    public Event(String input, boolean isDone) throws DateTimeParseException {
        super(isDone);
        String[] strArr = input.split("/at");
        this.description = strArr[0].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            this.at = LocalDateTime.parse(strArr[1].trim(), formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Wrong date format");
            throw e;
        }
    }

    public Event(String input, String at, boolean isDone) {
        super(isDone);
        this.description = input;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            this.at = LocalDateTime.parse(at);
        } catch (DateTimeParseException e) {
            System.out.println("Wrong date format");
            throw e;
        }
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.Event;
    }

    @Override
    public String toDataForm() {
        String done = this.isDone ? "1" : "0";
        return "E|" + done + "|" + this.description + "|" + this.at + "\n";
    }

    @Override
    public String toString() {
        String date  = this.at.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
        String head = "[E][" + this.getStatusIcon() + "] ";
        String body = this.description + " (at: " + date + ")";
        return head + body;
    }
}
