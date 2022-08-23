package jduke.task;

import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task {
    private LocalDate date;
    private LocalTime time;

    public Deadline(String description, String timing) {
        super(description);
        String[] timingParams = timing.split(" ");
        if (timingParams.length == 2) {
            this.time = LocalTime.parse(timingParams[1], Task.inputTimeFormatter);
        }
        this.date = LocalDate.parse(timingParams[0], Task.inputDateFormatter);
    }
    @Override
    protected String getType() {
        return "D";
    }

    @Override
    public boolean isEqualDate(LocalDate date) {
        return this.date.equals(date);
    }

    @Override
    public String toStorageFormat() {
        return String.format(
                "D | %d | %s | %s%s",
                (this.isCompleted ? 1 : 0),
                this.description,
                this.date.format(Deadline.inputDateFormatter),
                (this.time == null ? "" : " " + this.time.format(Task.inputTimeFormatter)));
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s%s)",
                this.getType(), (this.isCompleted ? "X" : " "), this.description,
                this.date.format(Task.outputDateFormatter),
                (this.time == null ? "" : " " + this.time.format(Task.outputTimeFormatter)));
    }
}
