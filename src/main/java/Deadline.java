import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Deadline extends Todo{
    private Date date;
    public Deadline(String title, Date date) {
        super(title);
        this.date = date;
    }
    public String toString() {
        return String.format("%s (by: %s)", super.toString().replace("[T]", "[D]"),
                new SimpleDateFormat("MMM dd yyyy HH:mm").format(this.date));
    }
}
