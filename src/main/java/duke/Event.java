package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

import duke.exception.DukeInvalidSaveDataException;

/**
 * Represents an event that will happen. Has a description, date
 * that the event occurs, and can be marked as done or undone.
 */
public class Event extends Task {
    private static final char EVENT_TAG = 'E';
    protected LocalDate on;

    public Event(String description, String on) {
        this(description, on, false);
    }

    /**
     * Creates an event with the given description, date, and done status.
     *
     * @param description
     * @param on
     * @param isDone
     */
    public Event(String description, String on, boolean isDone) {
        super(description, EVENT_TAG, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.on = LocalDate.parse(Arrays.stream(on.split(" ")).skip(1)
                .collect(Collectors.joining("")), formatter);
    }

    /**
     * Converts save string data to a Event object.
     * The save string data is in the format:
     * <p>
     *
     * <pre>
     * E,"&lt;description&gt;","&lt;on&gt;"
     * </pre>
     * <p>
     *
     * @param saveString the save string data
     * @return the new Event object created from saveString
     * @throws DukeException
     */
    public static Event fromSaveString(String saveString) throws DukeInvalidSaveDataException {
        String[] splitSaveString = saveString.split("(\",\")|(\",)|(,\")|\"");
        if (splitSaveString.length != 3) {
            throw new DukeInvalidSaveDataException();
        }
        String description = splitSaveString[1];
        String on = "on " + splitSaveString[2];
        boolean isDone = splitSaveString[0].endsWith("1");
        return new Event(description, on, isDone);
    }

    @Override
    public String toString() {
        return super.toString() + " (on " + on.toString() + ")";
    }

    /*
     * (non-Javadoc)
     *
     * @see duke.Task#toSaveData()
     */
    @Override
    public String toSaveData() {
        return super.toSaveData() + String.format(",\"%s\"", this.on.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || o instanceof Event == false) {
            return false;
        }
        Event event = (Event) o;
        return super.equals(o)
                && on.equals(event.on);
    }
}
