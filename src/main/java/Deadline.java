import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task {
    private String time;

    private static final String metaPattern = "^(.+)(\\s?+/by\\s?+)(.+)?$";
    private static String extractDescription(String meta) {
        Matcher m = Pattern.compile(metaPattern).matcher(meta);
        if (!m.find()) return ""; // THROW ERROR
        return m.group(1);
    }

    public Deadline(String meta) {
        super(extractDescription(meta));
        Matcher m = Pattern.compile(metaPattern).matcher(meta);
        if (!m.find()) return; // THROW ERROR
        String delimiter = m.group(2); // USE FOR ERROR HANDLING
        this.time = m.group(3);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.time + ")";
    }
}