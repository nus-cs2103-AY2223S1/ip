import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Task {
    private String time;

    private static final String metaPattern = "(^.+)(\\s?+/at\\s?+)(.+)?$";
    private static String extractDescription(String meta) throws DukeException {
        Matcher m = Pattern.compile(metaPattern).matcher(meta);
        if (!m.find()) {
            throw new DukeException("You need to use \"/at\" to specify when the event is");
        }
        return m.group(1);
    }

    public Event(String meta) throws DukeException {
        super(extractDescription(meta));
        Matcher m = Pattern.compile(metaPattern).matcher(meta);
        if (!m.find()) {
            throw new DukeException("You need to use \"/at\" to specify when the event is");
        }
        String time = m.group(3);
        if (time == null) throw new DukeException("You didn't specify the time.");
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time + ")";
    }
}