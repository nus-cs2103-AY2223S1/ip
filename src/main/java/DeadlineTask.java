public class DeadlineTask extends Task {
    private static final String TASK_TYPE = "D";
    private final String date;
    DeadlineTask(String taskName, String date) throws EmptyTaskException, InvalidDeadlineException {
        super(taskName);
        this.date = date;
        if (super.getTaskName().equals("")) {
            throw new EmptyTaskException();
        }
        if (this.date.equals("")) {
            throw new InvalidDeadlineException();
        }
    }

    DeadlineTask(String taskName, String date, boolean status) throws EmptyTaskException, InvalidDeadlineException {
        super(taskName, status);
        this.date = date;
        if (super.getTaskName().equals("")) {
            throw new EmptyTaskException();
        }
        if (this.date.equals("")) {
            throw new InvalidDeadlineException();
        }
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public String getFormattedString() {
        return TASK_TYPE + " | " + (getStatus() ? 1 : 0) + " | " + getTaskName() + " | " + this.date + "\n";
    }

    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString() + " (by:" + date + ")";
    }
}