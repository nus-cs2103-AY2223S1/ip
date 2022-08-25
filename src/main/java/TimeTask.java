import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeTask extends Task {

    public LocalDateTime date;

    public TimeTask(String description, String icon, LocalDateTime date) {
        super(description, icon);
        this.date = date;
    }

    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }
    
    
}
