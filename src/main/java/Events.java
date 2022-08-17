public class Events extends Task {
    private static final int SKIP_EVENT_COMMAND = 6;
    private static final int SKIP_AT_COMMAND = 4;
    private final String time;

    private static String process(String command, boolean getDetail) {
        String detail = command.substring(SKIP_EVENT_COMMAND,
                command.indexOf("/at"));
        String time = command.substring(command.lastIndexOf("/at ") + SKIP_AT_COMMAND);
        return (getDetail) ? detail : time;
    }

    Events(String detail, boolean isDone, String time) {
        super(detail, isDone);
        this.time = time;
    }

    Events(String command) {
        super(process(command, true));
        this.time = process(command, false);
    }

    @Override
    public Task markDone() {
        return new Events(super.getDetail(), true, this.time);
    }

    @Override
    public Task unmarkDone() {
        return new Events(super.getDetail(), false, this.time);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + String.format("(at: %s)", this.time);
    }
}
