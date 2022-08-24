import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private String date;
    private LocalDate localDate;
    private String formattedDate;


    public Deadline(String description, String date, boolean isDone) {
        super(description, "D", isDone);
        this.date = date;
        localDate = LocalDate.parse(date);
        formattedDate = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.formattedDate);
    }

    @Override
    public String getCsvString() {
        return String.format("deadline %s /by %s", super.getCsvString(), date);
    }
}
