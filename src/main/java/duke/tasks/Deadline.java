package duke.tasks;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime deadline;

    public Deadline(String description, String date, String time) {
        super(description);
        this.deadline = LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(time));
    }

    public Deadline(String description, String date, String time, boolean isDone) {
        super(description, isDone);
        this.deadline = LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(time));
    }

    public Deadline(String instructions) {
        super(instructions.split("/by")[0]);
        String date = instructions.split("/by")[1].trim().split(" ")[0];
        String time = instructions.split("/by")[1].trim().split(" ")[1];
        this.deadline = LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(time));
    }

    public Deadline(String description, LocalDateTime dateTime, boolean isDone) {
        super(description, isDone);
        this.deadline = dateTime;
    }

    public String getDeadline() {
        return deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + getDeadline() + ")";
    }

    @Override
    public String save() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + deadline;
    }
}
