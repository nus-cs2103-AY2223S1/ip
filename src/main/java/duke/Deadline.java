package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    String date;

    public Deadline(String description, boolean isDone, String date) {
        super(description.trim());
        this.isDone = isDone;
        this.date = date.trim();
        Task.taskCount++;
    }


    @Override
    public String toString() {
        return String.format("D | %s | %s | %s", this.getStatusIcon(), this.description, date);
    }


}
