package functional;

import technical.SaveLine;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    /**
     * Construct functional.Task with a fixed name.
     *
     * @param name The name of the task.
     */
    public Event(String name, LocalDateTime startTime, LocalDateTime endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(SaveLine line) {
        super(line);
        startTime = LocalDateTime
            .parse(line.getValue("startTime"), DateTimeFormatter.ofPattern("d MMM yyyy 'at' HH:mm:ss"));
        endTime = LocalDateTime
            .parse(line.getValue("endTime"), DateTimeFormatter.ofPattern("d MMM yyyy 'at' HH:mm:ss"));
    }

    /**
     * Shows the event name and status as a String.
     *
     * @return A String with the task name and status.
     */
    public String toString() {
        return String.format("[E]%s (from %s to %s)", super.toString(),
            startTime.format(DateTimeFormatter.ofPattern("d MMM yyyy 'at' HH:mm:ss")),
            endTime.format(DateTimeFormatter.ofPattern("d MMM yyyy 'at' HH:mm:ss")));
    }

    @Override
    public SaveLine toData() {
        SaveLine ret = super.toData();
        ret.setInfoType("event");
        ret.addNameData("startTime", startTime.format(DateTimeFormatter.ofPattern("d MMM yyyy 'at' HH:mm:ss")));
        ret.addNameData("endTime", endTime.format(DateTimeFormatter.ofPattern("d MMM yyyy 'at' HH:mm:ss")));
        return ret;
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof Event) {
            Event rhsDeadline = (Event) rhs;
            return toData().equals(rhsDeadline.toData());
        }
        return false;
    }
}
