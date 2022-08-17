import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task {
    private String time;

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
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.time + ")";
    }
}