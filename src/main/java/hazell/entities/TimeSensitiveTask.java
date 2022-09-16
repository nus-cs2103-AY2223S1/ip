package hazell.entities;

/**
 * A superclass to be inherited by tasks that require a time.
 */
public abstract class TimeSensitiveTask extends Task {

    protected TimeSensitiveTask(boolean isDone, String description) {
        super(isDone, description);
    }

    public abstract void postpone(String time);
}
