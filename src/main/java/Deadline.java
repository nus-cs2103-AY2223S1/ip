import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task{
    protected LocalDateTime time;

    public Deadline(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    @Override
    public String getStatus() {
        if(this.isDone) {
            return "[D][X]";
        } else {
            return "[D][ ]";
        }
    }
    @Override
    public String toString() {
        return this.getStatus() + " " + this.description + " (by: " +
                this.time.format(DateTimeFormatter.ofPattern("hh:mm a 'on' dd/MM/yyyy")) + ")";
    }
}
