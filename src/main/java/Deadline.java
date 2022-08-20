import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime byDateTime;

    public Deadline(String description, LocalDateTime byDateTime) {
        super(description);
       this.byDateTime = byDateTime;
    }
    
  

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
        String formattedDateTime = this.byDateTime.format(formatter);
        return String.format("[D]%s (by: %s)", super.toString(), formattedDateTime);
    }

    @Override
    public String toFileFormatString() {
        return "D" + super.toFileFormatString() + description + "|" + byDateTime ;
    }
}