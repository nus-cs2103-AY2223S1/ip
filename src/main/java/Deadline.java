import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected final String TAG = "[D]";
    protected static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Deadline(String descriptor, String due) {
        super(descriptor);
        this.time = LocalDateTime.parse(due, DTF);
    }
    @Override
    public String toString() {
        return TAG + super.toString() + " (by: " +
                this.time.format(DTF) + ")";
    }
}
