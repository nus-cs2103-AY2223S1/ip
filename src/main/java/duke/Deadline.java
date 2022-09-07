package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

import duke.exception.DukeInvalidSaveDataException;

/**
 * Represents a deadline to be completed. Has a description, date
 * that the deadline is due, and can be marked as done or undone.
 */
public class Deadline extends Task {
    private static final char DEADLINE_TAG = 'D';
    protected LocalDate by;

    public Deadline(String description, String by) {
        this(description, by, false);
    }

    /**
     * Creates a deadline with the given description, date, and done status.
     *
     * @param description description of the deadline
     * @param by          date that the deadline is due
     * @param isDone      whether the deadline is done
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, DEADLINE_TAG, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.by = LocalDate.parse(Arrays.stream(by.split(" ")).skip(1)
                .collect(Collectors.joining("")), formatter);
    }

    /**
     * Converts save string data to a Deadline object.
     * The save string data is in the format:
     * <p>
     *
     * <pre>
     * D,"&lt;description&gt;","&lt;by&gt"
     * </pre>
     * <p>
     *
     * @param saveString the save string data
     * @return the new Deadline object created from saveString
     * @throws DukeInvalidSaveDataException
     */
    public static Deadline fromSaveString(String saveString) throws DukeInvalidSaveDataException {
        String[] splitSaveString = saveString.split("(\",\")|(\",)|(,\")|\"");
        if (splitSaveString.length != 3) {
            throw new DukeInvalidSaveDataException();
        }
        assert splitSaveString[0].equals("D") : "Save data is not a deadline.";
        assert splitSaveString[0].endsWith("1") || splitSaveString[0].endsWith("0")
                : "Save data contains invalid isDone value.";
        String description = splitSaveString[1];
        String by = "by " + splitSaveString[2];
        boolean isDone = splitSaveString[0].endsWith("1");
        return new Deadline(description, by, isDone);
    }

    /*
     * (non-Javadoc)
     *
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
        return super.equals(o)
                && by.equals(deadline.by);
    }
}
