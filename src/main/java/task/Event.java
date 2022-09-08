package task;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents an event with a specified date and time.
 */
public class Event extends Task {

    public static final String SYMBOL = "E";
    private static final String FORMAT = "[E]%s (at: %s)";
    private static final String WRITE_FORMAT = "E | %d | %s | %s\n";

    private String period;

    public Event(String s1, String s2) {
        super(s1);
        this.period = s2;
    }

    @Override
    public void write(FileWriter fw) throws IOException {
        String str = String.format(Event.WRITE_FORMAT, this.getDoneInt(), this.getName(), this.period);
        fw.write(str);
    }

    @Override
    public String toString() {
        return String.format(Event.FORMAT, super.toString(), this.period);
    }
}
