package hazell.entities;

public abstract class TimeSensitiveTask extends Task {

    protected TimeSensitiveTask(boolean isDone, String description) {
        super(isDone, description);
    }

    public abstract void postpone(String time);
}
