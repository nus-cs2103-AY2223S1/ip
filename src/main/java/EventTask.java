import java.util.Map;

public class EventTask extends Task {
    private final String time;

    public EventTask(Map<String, String> args, boolean isDone) {
        super( 'E', args.get("description"), isDone);
        time = args.get("at");
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), time);
    }

    @Override
    public String toData() {
        return String.format("%s | %s", super.toData(), time);
    }
}