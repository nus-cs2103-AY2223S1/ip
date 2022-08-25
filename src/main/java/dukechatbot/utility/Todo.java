package dukechatbot.utility;
public class Todo  extends Task {
    protected final static String TAG = "[T]";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return TAG + super.toString();
    }
}
