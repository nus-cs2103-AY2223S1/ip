package dukechatbot.utility;

/**
 * The Todo class implements the type of tasks that are to be done
 * but do not have an associated deadline or time component to them.
 *
 * @author A0233290M
 * @version Week3
 */
public class Todo  extends Task {
    /**
     * Defines the Tag associated with this type of tasks.
     */
    protected final static String TAG = "[T]";

    /**
     * Constructs the instance of Todo with the associated description.
     * @param description describes the instance of Todo that is to be created.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public String toString() {
        return TAG + super.toString();
    }
}
