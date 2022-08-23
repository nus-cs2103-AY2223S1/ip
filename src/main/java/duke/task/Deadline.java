package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate date;
    protected String time;
    protected String dateTime;

    public Deadline(String description, String dateTime) {
        super(description);
        String[] details = dateTime.split(" ");
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/MM/yyyy");
        this.date = LocalDate.parse(details[0], inputFormat);
        this.time = details[1];
        this.dateTime = dateTime;

    }

    public static Deadline parseFile(String string) {
        String[] details = string.split(" \\| ");
        Deadline deadline = new Deadline(details[2], details[3]);
        if (details[1].equals("1")) {
            deadline.markAsDone();
        }
        return deadline;
    }

    @Override
    public String toDataFormat() {
        String completed = "0";
        if (this.getStatusIcon().equals("X")) {
            completed = "1";
        }
        return "D | " + completed + " | " + this.getDescription() + " | " + this.dateTime;
    }


    @Override
    public String toString() {
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString()
                + " (by: " + time + " " + date.format(displayFormat) + ")";
    }
}