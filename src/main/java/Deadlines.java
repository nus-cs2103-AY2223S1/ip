import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    private static final String timeId = "/by ";
    private static final String ID = "[D]";
    private final LocalDateTime time;

    Deadlines(String detail, boolean isDone, LocalDateTime time) {
        super(detail, isDone);
        this.time = time;
    }

    Deadlines(String command) throws DukeException {
        super(Parser.extractDetail(command, timeId));
        try {
            this.time = Parser.extractDateTime(command, timeId);
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException(new BotUI().invalidDateFormat());
        }
    }

    @Override
    public Task markDone() {
        return new Deadlines(super.getDetail(), true, this.time);
    }

    @Override
    public Task unmarkDone() {
        return new Deadlines(super.getDetail(), false, this.time);
    }

    @Override
    String getId() {
        return ID;
    }

    @Override
    LocalDateTime getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return ID + super.toString()
                + String.format("(at: %s)", this.time.format(formatter));
    }
}
