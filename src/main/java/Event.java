import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate d;
    private LocalTime t;
    private boolean hasTime;

    Event(String description, String at) throws DukeException {
        super(description);
        String[] dateAndTime = at.split(" ");
        try {
            this.d = LocalDate.parse(dateAndTime[0]);
            if (dateAndTime.length == 2) {
                this.t = LocalTime.parse(dateAndTime[1], DateTimeFormatter.ofPattern("HHmm"));
                this.hasTime = true;
            } else {
                this.hasTime = false;
            }
        } catch (java.time.format.DateTimeParseException e) {
            throw new DukeException("Please enter a valid date and time YYYY-MM-DD HHMM");
        }
    }

    public static Event taskFromSave(String saveString) throws DukeException {
        String[] tokens = saveString.split(" \\| ");
        String time = tokens[3].equals("true") ? tokens[4] + " " + tokens[5] : tokens[4];
        Event event = new Event(tokens[2], time);
        if (tokens[1].equals("1")) {
            event.markDone();
        }
        return event;
    }

    @Override
    public String saveString() {
        return "E | " + super.saveString() +  " | " + this.hasTime + " | " + this.d + " | " 
                + (this.hasTime ? this.t.format(DateTimeFormatter.ofPattern("HHmm")) : "");
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " 
                + this.d.format(DateTimeFormatter.ofPattern(("MMM d yyyy")))
                + (this.hasTime ? " " + this.t : "")  
                + ")";
    }
}
