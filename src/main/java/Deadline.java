import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private String by;

    public Deadline(String description, String by) {
        super(description);
        LocalDate today = LocalDate.parse(by);
        String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MMM-yy"));
        this.by = formattedDate;
    }

    @Override
    public String getTime() {
        return by;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        String format = "dd-MM-yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
