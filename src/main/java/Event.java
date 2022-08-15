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

    private static String[] getArguments(String str) throws DwukeException {
        int index = str.indexOf("/at");

        if (index == -1) throw new DwukeException("'/at' not fwound");

        try {
            String description = str.substring(0, index - 1);
            String date = str.substring(index + 4);
            return new String[]{description, date};
        } catch (StringIndexOutOfBoundsException e) {
            throw new DwukeException("da descwiption and date cannot be empty");
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

