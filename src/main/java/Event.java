import java.util.List;

public class Event extends Task {
    private final String ICON = "[E]";

    Event(List<String> task) {
        super(task);
    }

    @Override
    public String toString() {
        return this.ICON + super.toString();
    }
}
