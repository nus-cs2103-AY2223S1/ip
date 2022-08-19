import java.util.Arrays;

public class Event extends Task {
    private String at;

    public Event(boolean isDone, String[] args) throws DukeException {
        super(args, "event", isDone, Arrays.stream(args).takeWhile(x -> !x.contains("/")).toArray(String[]::new));
        String[] curArgs = Arrays.stream(args).dropWhile(x -> !x.contains("/")).toArray(String[]::new);
        if (curArgs.length == 0 || !curArgs[0].equals("/at")) {
            throw new DukeException("☹ OOPS!!! There is no /at argument for deadline :(");
        }
        this.at = Arrays.stream(curArgs).skip(1).reduce("", (x, y) -> x + " " + y);
        if (this.at.isEmpty()) {
            throw new DukeException("☹ OOPS!!! No time is specified for the event :(");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (at:" + this.at + ")";
    }
}
