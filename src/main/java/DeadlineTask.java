public class DeadlineTask extends Task {
    private final String date;
    DeadlineTask(String task, String date) throws EmptyTaskException, InvalidDeadlineException {
        super(task);
        this.date = date;
        if (super.getName().equals("")) {
            throw new EmptyTaskException();
        }
        if (this.date.equals("")) {
            throw new InvalidDeadlineException();
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + date + ")";
    }
}