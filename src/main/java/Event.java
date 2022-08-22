import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Event extends Todo{
    private Date date;
    public Event(String title, Date date) {
        super(title);
        this.date = date;
    }
    public String toString() {
        return String.format("%s (at: %s)", super.toString().replace("[T]", "[E]"),
                new SimpleDateFormat("MMM dd yyyy HH:mm").format(this.date));
    }
}
