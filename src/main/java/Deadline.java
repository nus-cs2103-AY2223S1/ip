/**
 * This class encapsulates a deadline set by the user.
 */
public class Deadline extends Task {
    private String date;

    Deadline(String str) throws DwukeException {
        super(str);
        String[] arguments = getArguments(str);
        this.changeDescription(arguments[0]);
        this.date = arguments[1];
    }

    private static String[] getArguments(String str) throws DwukeException {
        int index = str.indexOf("/by");

        if (index == -1) throw new DwukeException("oops!!! '/by' not fwound");

        try {
            String description = str.substring(0, index - 1);
            String date = str.substring(index + 4);
            return new String[]{description, date};
        } catch (StringIndexOutOfBoundsException e) {
            throw new DwukeException("oops!!! da descwiption and date cannot be empty");
        }
    }

    /**
     * Returns the String representation of this deadline.
     *
     * @return A String representing this deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }
}
