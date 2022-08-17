public class Deadlines extends Task {
    private static final int SKIP_DEADLINE_COMMAND = 9;
    private static final int SKIP_BY_COMMAND = 4;
    private final String time;

    private static String process(String command, boolean getDetail) {
        String detail = command.substring(SKIP_DEADLINE_COMMAND, command.indexOf("/by"));
        String time = command.substring(command.lastIndexOf("/by ") + SKIP_BY_COMMAND);
        return (getDetail) ? detail : time;
    }

    Deadlines(String detail, boolean isDone, String time) {
        super(detail, isDone);
        this.time = time;
    }

    Deadlines(String command) {
        super(process(command, true));
        this.time = process(command, false);
    }

    @Override
    public Task markDone() {
        return new Deadlines(super.getDetail(), true, this.time);
    }

    @Override
    public Task unmarkDone() {
        return new Deadlines(super.getDetail(), false, this.time);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + String.format("(by: %s)", this.time);
    }
}
