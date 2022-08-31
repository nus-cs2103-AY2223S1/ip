import java.util.Map;

public class TodoTask extends Task {
    public TodoTask(Map<String, String> args, boolean isDone) {
        super('T', args.get("description"), isDone);
    }
}