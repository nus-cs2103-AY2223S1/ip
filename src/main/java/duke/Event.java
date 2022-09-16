package duke;

import java.time.LocalDateTime;

import duke.Task;

public class Event extends Task {
    private final LocalDateTime startTime;

    public Event(String name, boolean initialComplete, String startTimeAsText) {
        super(name, initialComplete);
        this.startTime = LocalDateTime.parse(startTimeAsText);
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at %s)", super.toString(), this.startTime.toString());
    }

    /**
     * Converts this Event to a String to be stored in a .txt file.
     *
     * @return the String representation of this Event
     */
    @Override
    public String toFileRepresentation() {
        return String.format("E|%d|%s|%s",
                this.isComplete() ? 1 : 0, this.getName(), this.startTime.toString());
    }
}