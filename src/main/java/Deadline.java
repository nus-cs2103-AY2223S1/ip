import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task {
    private LocalDate time;

    private static final String metaPattern = "(^.+)(\\s?+/by\\s?+)(.+)?$";
    private static String extractDescription(String meta) throws DukeException {
        Matcher m = Pattern.compile(metaPattern).matcher(meta);
        if (!m.find()) {
            throw new DukeException("You need to use \"/by\" to specify when the event is");
        }
        return m.group(1);
    }

    public Deadline(String meta) throws DukeException {
        super(extractDescription(meta));
        Matcher m = Pattern.compile(metaPattern).matcher(meta);
        if (!m.find()) {
            throw new DukeException("You need to use \"/by\" to specify when the event is");
        }
        String time = m.group(3);
        if (time == null) throw new DukeException("You didn't specify the time.");
        try {
            this.time = LocalDate.parse(time);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date/time format");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}