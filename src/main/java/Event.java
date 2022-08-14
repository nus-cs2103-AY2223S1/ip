/**
 * This class encapsulates an event set by the user.
 */
public class Event extends Task {
    private String date;

    Event(String str) throws DwukeException {
        super(str);
        String[] arguments = getArguments(str);
        this.changeDescription(arguments[0]);
        this.date = arguments[1];
    }

    /**
     * Splits the given String into description and date.
     *
     * @param str The String to split.
     * @return An array containing the description and date.
     * @throws DwukeException If '/at' is not found, or if the description or date is empty.
     */
    public static String[] getArguments(String str) throws DwukeException {
        int index = str.indexOf("/at");

        if (index == -1) throw new DwukeException("oops!!! '/at' not fwound");

        try {
            String description = str.substring(0, index - 1);
            String date = str.substring(index + 4);
            return new String[]{description, date};
        } catch (StringIndexOutOfBoundsException e) {
            throw new DwukeException("oops!!! da descwiption and date cannot be empty");
        }
    }

    /**
     * Returns the String representation of this event.
     *
     * @return A String representing this event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date + ")";
    }
}

