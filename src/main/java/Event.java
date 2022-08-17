import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event extends Task {
    private String time;

    private static final String metaPattern = "^(.+)(\\s?+/at\\s?+)(.+)?$";
    private static String extractDescription(String meta) {
        Matcher m = Pattern.compile(metaPattern).matcher(meta);
        if (!m.find()) return ""; // THROW ERROR
        return m.group(1);
    }

    public Event(String meta) {
        super(extractDescription(meta));
        Matcher m = Pattern.compile(metaPattern).matcher(meta);
        if (!m.find()) return; // THROW ERROR
        String delimiter = m.group(2); // USE FOR ERROR HANDLING
        this.time = m.group(3);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time + ")";
    }
}