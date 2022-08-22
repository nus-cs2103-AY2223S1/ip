import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    private LocalDate date;

    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public Event(String description, String date) throws DukeException {
        super(description);
        this.date = getDate(date);
    }

    public boolean onThisDate(LocalDate date){
        return this.date.equals(date);
    }

    public String toStringDate() {
        return super.toString();
    }

    @Override
    public String parseTask() {
        return "E" + super.parseTask() + "/" + this.date;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    private static LocalDate getDate(String date) throws DukeException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e){
            throw new DukeException("☹ OOPS!!! Please format your date as yyyy-mm-dd format (e.g., 2019-10-15)");
        }
    }
}
