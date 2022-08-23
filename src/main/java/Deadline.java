import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate date;
    protected String time;

    public Deadline(String description, String dateTime) {
        super(description);
        String[] details = dateTime.split(" ");
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/MM/yyyy");
        this.date = LocalDate.parse(details[0], inputFormat);
        this.time = details[1];

    }

    @Override
    public String toString() {
        DateTimeFormatter displayFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString()
                + " (by: " + time + " " + date.format(displayFormat) + ")";
    }
}