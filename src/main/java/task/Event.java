package task;

import java.io.FileWriter;
import java.io.IOException;

/**
 *  A task that takes place over a specified period of time.
 */
public class Event extends Task {

    public static final String SYMBOL = "E";
    private static final String FORMAT = "[E]%s (at: %s)";
    private static final String WRITE_FORMAT = "E | %d | %s | %s\n";

    private final String period;

    /**
     * Creates an event, which is a task that takes place over a specified period of time.
     */
    public Event(String name, String period) {
        super(name);
        this.period = period;
    }

    @Override
    public void write(FileWriter fileWriter) throws IOException {
        String line = String.format(Event.WRITE_FORMAT, this.getDoneInt(), this.getName(), this.period);
        fileWriter.write(line);
    }

    @Override
    public String toString() {
        return String.format(Event.FORMAT, super.toString(), this.period);
    }

    @Override
    public boolean equals(Task task) {
        if (task == null) {
            return false;
        } else if (!(task instanceof Event)) {
            return false;
        } else if (this.getName().equals(task.getName())) {
            // to compare the periods of the two events
            Event event = (Event) task;
            return this.period.equals(event.period);
        } else {
            return false;
        }
    }
}
