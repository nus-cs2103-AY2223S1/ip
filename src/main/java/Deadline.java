import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;
    public Deadline(String description, boolean done, String by)
    {
        super(description, done);
        this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public char getType()
    {
        return 'D';
    }

    @Override
    public String getDetail()
    {
        return by.toString();
    }
}