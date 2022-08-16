public class Events extends Task {
    private final String time;

    private static String process(String command, boolean getDetail) {
        String detail = command.substring(command.lastIndexOf("event "),
                command.indexOf("/at"));
        String time = command.substring(command.lastIndexOf("/at ") + 4);
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
