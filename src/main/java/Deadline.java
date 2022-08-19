import java.util.Arrays;

public class Deadline extends Task {
    private String by;

    public Deadline(boolean isDone, String[] args) throws DukeException {
        super(args, "deadline", isDone, Arrays.stream(args).takeWhile(x -> !x.contains("/")).toArray(String[]::new));
        String[] curArgs = Arrays.stream(args).dropWhile(x -> !x.contains("/")).toArray(String[]::new);
        if (curArgs.length == 0 || !curArgs[0].equals("/by")) {
            throw new DukeException("☹ OOPS!!! There is no /by argument for deadline :(");
        }
        this.by = Arrays.stream(curArgs).skip(1).reduce("", (x, y) -> x + " " + y);
        if (this.by.isEmpty()) {
            throw new DukeException("☹ OOPS!!! No time is specified for the deadline :(");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (by:" + this.by + ")";
    }
}
