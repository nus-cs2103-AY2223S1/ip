import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task {
    protected DateTimeFormatter inputDateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
    protected DateTimeFormatter outputDateFormatter = DateTimeFormatter.ofPattern("E, d MMM yyyy");
    protected String type;
    protected String description;
    protected LocalDate dateTime;
    protected   boolean isDone;

    Task (String description) {
        this.description = description;
        this.isDone = false;
    }
    Task (String type, String description, String dateTime) throws InvalidDateException {
        this.description = description;
        this.dateTime = convert(dateTime);
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public LocalDate convert(String date) throws InvalidDateException { //assumes format is in d/M/yyyy
        try {
            LocalDate currentDate = LocalDate.now();
            LocalDate taskDate = LocalDate.parse(date, inputDateFormatter);
            if (taskDate.isBefore(currentDate)) {
                throw new InvalidDateException("Date should be a future date, not one in the past");
            }
            return taskDate;
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("Date is formatted wrongly");
        }
    }

    @Override
    public String toString() {
        char mark;
        if (this.isDone) {
            mark = 'X';
        } else {
            mark = ' ';
        }
        return ("[" + mark + "] " + this.getDescription());
    }
}