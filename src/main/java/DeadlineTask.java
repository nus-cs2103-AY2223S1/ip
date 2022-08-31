import java.util.Map;

public class DeadlineTask extends Task {
    private final String deadline;

    public DeadlineTask(Map<String, String> args, boolean isDone) {
        super( 'D', args.get("description"), isDone);
        deadline = args.get("by");
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), deadline);
    }

    @Override
    public String toData() {
        return String.format("%s | %s", super.toData(), deadline);
    }
}