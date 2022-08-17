public class Todo  extends Task {
    protected final String TAG = "[T]";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return TAG + super.toString();
    }
}
