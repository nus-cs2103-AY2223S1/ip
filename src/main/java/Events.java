import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {

    private static final String timeId = "/at ";
    private static final String ID = "[E]";
    private final LocalDateTime time;

    Events(String detail, boolean isDone, LocalDateTime time) {
        super(detail, isDone);
        this.time = time;
    }

    Events(String command) {
        super(Parser.extractDetail(command, timeId));
        this.time = Parser.extractDateTime(command, timeId);
    }

    @Override
    String getId() {
        return ID;
    }

    @Override
    String getTime() {
        return this.time;
    }

    @Override
    Task markDone() {
        return new Events(super.getDetail(), true, this.time);
    }

    @Override
    Task unmarkDone() {
        return new Events(super.getDetail(), false, this.time);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return ID + super.toString()
                + String.format("(at: %s)", this.time.format(formatter));
    }
}
