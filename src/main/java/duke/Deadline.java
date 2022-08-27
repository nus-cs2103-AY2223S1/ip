package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Represents a deadline to be completed. Has a description, date
 * that the deadline is due, and can be marked as done or undone.
 */
public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) {
        this(description, by, false);
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, 'D', isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.by = LocalDate.parse(Arrays.stream(by.split(" ")).skip(1)
                .collect(Collectors.joining("")), formatter);
    }

    /**
     * Converts save string data to a Deadline object.
     * The save string data is in the format:
     * <p>
     * <pre>
     * D,"<description>","<by>"
     * </pre>
     * <p>
     * 
     * @param saveString the save string data
     * @return the new Deadline object created from saveString
     * @throws DukeException
     */
    public static Deadline fromSaveString(String saveString) throws DukeException {
        String[] splitSaveString = saveString.split("(\",\")|(\",)|(,\")|\"");
        if(splitSaveString.length != 3) {
            throw new DukeException("Tried to read unexpected save data.");
        }
        String description = splitSaveString[1];
        String by = "by " + splitSaveString[2];
        boolean isDone = splitSaveString[0].endsWith("1");
        return new Deadline(description, by, isDone);
    }

    /* (non-Javadoc)
     * @see duke.Task#toSaveData()
     */
    @Override
    public String toSaveData() {
        return super.toSaveData() + String.format(",\"%s\"", this.by.toString());
    }

    @Override
    public String toString() {
        return super.toString() + " (by " + by.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || o instanceof Deadline == false) {
            return false;
        }
        Deadline deadline = (Deadline) o;
        return super.equals(o) &&
                by.equals(deadline.by);
    }
}
