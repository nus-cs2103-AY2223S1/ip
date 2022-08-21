import java.time.LocalDate;
import java.time.DateTimeException;

/**
 * This class encapsulates a deadline set by the user.
 */
public class Deadline extends Task {
    private LocalDate date;

    Deadline(String str) throws DwukeException, DateTimeException {
        super(str);
        String[] arguments = getArguments(str);
        this.changeDescription(arguments[0]);
        this.date = Date.parse(arguments[1]);
    }

    Deadline(String description, boolean isDone, String date) throws DwukeException {
        super(description, isDone);
        this.date = Date.parse(date);
    }

    private static String[] getArguments(String str) throws DwukeException, DateTimeException {
        int index = str.indexOf("/by");

        if (index == -1) throw new DwukeException("'/by' not fwound");

        try {
            String description = str.substring(0, index - 1);
            String date = str.substring(index + 4);
            return new String[]{description, date};
        } catch (StringIndexOutOfBoundsException e) {
            throw new DwukeException("da descwiption and date cannot be empty");
        }
    }

    /**
     * Returns a Deadline after decoding the String.
     *
     * @param s The String to decode.
     * @return The Deadline decoded from the String.
     * @throws DwukeException If the String is empty.
     */
    public static Deadline decode(String s) throws DwukeException {
        String[] parts = s.split(";");
        boolean isDone = parts[0].equals("1");
        return new Deadline(parts[1], isDone, parts[2]);
    }

    /**
     * Encodes the Deadline into a String.
     *
     * @return The Deadline encoded as String.
     */
    @Override
    public String encode() {
        return "D;" + super.encode() + ";" + this.date;
    }

    /**
     * Returns the String representation of this deadline.
     *
     * @return A String representing this deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Date.format(this.date) + ")";
    }
}
