import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task{

    private String at;

    public Events(String description, String at) {
        super(description);
        LocalDate today = LocalDate.parse(at);
        String formattedDate = today.format(DateTimeFormatter.ofPattern("dd-MMM-yy"));
        this.at = formattedDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
