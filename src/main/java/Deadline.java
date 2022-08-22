import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private LocalDate date;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public Deadline(String description, String date) throws DukeException {
        super(description);
        this.date = getDate(date);
    }

    public boolean byThisDate(LocalDate date){
        return this.date.isBefore(date);
    }

    public String toStringDate() {
        return super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String parseTask() {
        return "D" + super.parseTask() + "/" + this.date;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    private static LocalDate getDate(String date) throws DukeException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e){
            throw new DukeException("☹ OOPS!!! Please format your date as yyyy-mm-dd format (e.g., 2019-10-15)");
        }
    }
}
