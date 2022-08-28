package duke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import duke.dukeExceptions.DukeException;

public class Deadline extends Task {
    private LocalDate date;
    private LocalTime time;
    private boolean hasTime;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        String[] dateAndTime = by.split(" ");
        try {
            this.date = LocalDate.parse(dateAndTime[0]);
            if (dateAndTime.length == 2) {
                this.time = LocalTime.parse(dateAndTime[1], DateTimeFormatter.ofPattern("HHmm"));
                this.hasTime = true;
            } else {
                this.hasTime = false;
            }
        } catch (java.time.format.DateTimeParseException e) {
            throw new DukeException("Please enter a valid date and time (YYYY-MM-DD HHMM) or date only (YYYY-MM-DD)");
        }
    }

    public static Deadline taskFromSave(String saveString) throws DukeException {
        String[] tokens = saveString.split(" \\| ");
        String time = tokens[3].equals("true") ? tokens[4] + " " + tokens[5] : tokens[4]; 
        Deadline deadline = new Deadline(tokens[2], time);
        if (tokens[1].equals("1")) {
            deadline.markDone();
        }
        return deadline;
    }
    
    @Override
    public String saveString() {
        return "D | " + super.saveString() + " | " + this.hasTime + " | " + this.date + " | " 
                + (this.hasTime ? this.time.format(DateTimeFormatter.ofPattern("HHmm")) : "");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " 
                + this.date.format(DateTimeFormatter.ofPattern(("MMM d yyyy"))) 
                + (this.hasTime ? " " + this.time : "") 
                + ")";
    }
}
