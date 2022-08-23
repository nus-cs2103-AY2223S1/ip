import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected final LocalDateTime time;

    public Deadline(String name, LocalDateTime time) {
        super(name);
        this.time = time;
    }

    public Deadline(String name, LocalDateTime time, boolean isDone) {
        super(name, isDone);
        this.time = time;
    }

    public String toString() {
        return String.format("[%s]%s (by: %s)", this.getType(), super.toString(), this.getTime());
    }

    public String toFileString() {
        return String.format("%s||%s", super.toFileString(), this.getTime());
    }

    public String getTime() {
        return this.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public String getType() {
        return "D";
    }
}