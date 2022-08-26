public class EventTask extends Task {
    private static final String TASK_TYPE = "E";
    private final String duration;
    EventTask(String taskName, String duration) throws EmptyTaskException, InvalidEventException {
        super(taskName);
        this.duration = duration;
        if (super.getTaskName().equals("")) {
            throw new EmptyTaskException();
        }
        if (this.duration.equals("")) {
            throw new InvalidEventException();
        }
    }

    EventTask(String taskName, String duration, boolean status ) throws EmptyTaskException, InvalidEventException {
        super(taskName, status);
        this.duration = duration;
        if (super.getTaskName().equals("")) {
            throw new EmptyTaskException();
        }
        if (this.duration.equals("")) {
            throw new InvalidEventException();
        }
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String getFormattedString() {
        return TASK_TYPE + " | " + (getStatus() ? 1 : 0) + " | " + getTaskName() + " | " + this.duration + "\n";
    }

    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString() + " (at:" + duration + ")";
    }
}