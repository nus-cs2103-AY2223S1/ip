package duke.task;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Deadline extends Task {
    private String date;
    private String time;

    private String name;
    public Deadline(String name, LocalDate date, LocalTime time) {
        super(name);
        this.date = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        this.time = DateTimeFormatter.ofPattern("hh:mm a").format(time);
        this.name = name;
    }

    public Deadline(String name, String date, String time) {
        super(name);
        this.time = time;
        this.date = date;
    }

    public String parse() {
        if (this.getCompleted()) {
            return "D#1#" + this.name + "#" + this.date + "#" + this.time;
        } else {
            return "D#0#" + this.name + "#" + this.date + "#" + this.time;
        }

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + " " + this.time + ")";
    }
}
