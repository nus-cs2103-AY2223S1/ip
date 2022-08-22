import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private static final DateTimeFormatter accept = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter to = DateTimeFormatter.ofPattern("dd MMM yyyy");
    protected LocalDate at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDate.parse(at, accept);
        print(this);
        updateTasks();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(to) + ")";
    }
}

