import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Event extends Task {
    private LocalDate at;

    public Event(boolean isDone, String[] args) throws DukeException {
        super(args, "event", isDone, Arrays.stream(args).takeWhile(x -> !x.contains("/")).toArray(String[]::new));
        String[] curArgs = Arrays.stream(args).dropWhile(x -> !x.contains("/")).toArray(String[]::new);
        if (curArgs.length <= 1 || !curArgs[0].equals("/at")) {
            throw new DukeException("☹ OOPS!!! There is no /at argument for deadline :(");
        }
        try {
            this.at = LocalDate.parse(curArgs[1]);
        } catch (java.time.format.DateTimeParseException exception) {
            throw new DukeException("☹ OOPS!!! Can't recognize the date :(. Please input the date in yyyy-mm-dd format.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d, yyyy")) + ")";
    }
}
