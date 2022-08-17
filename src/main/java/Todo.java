public class Todo extends Task {
    private static final String TAG = "[D]";
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return TAG + super.toString();
    }
}
