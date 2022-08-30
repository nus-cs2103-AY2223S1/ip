package task;
import java.time.LocalDateTime;

public class Deadline extends TimeTask {

    private final static String ICON = "D";

    public Deadline(String description, LocalDateTime time) {
        super(description, ICON, time);
    }

    @Override
    public String toString() {
        return String.format("[D]" + "[%s] " + super.toString()
                + " (by: " + super.getDate() + ")", super.getStatusIcon());
    }

    public String toSave() {
        return String.format("[D]" + "[%s] " + super.toString()
                + " (by: " + super.getDateSave() + ")", super.getStatusIcon());
    }
}
