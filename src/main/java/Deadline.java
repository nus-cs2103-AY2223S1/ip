import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDateTime time;
    public Deadline(String content, LocalDateTime time) {
        super(content);
        this.time = time;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String timeString = this.time.format(formatter);
        return this.isDone() ? "[D][X] " + this.getContent() + " by " + this.time
                : "[D][ ] " + this.getContent() + " by " + timeString;
    }
}
