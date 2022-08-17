import java.util.List;

public class Event extends Task {
    private final String ICON = "[E]";

    Event(List<String> task) throws DekuExceptions {
        super(task, "event");
    }

    @Override
    public String toString() {
        return this.ICON + super.toString();
    }
}
