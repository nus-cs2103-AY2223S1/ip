package duke.enums;

/**
 * Secondary commands that can be used by users in the duke application.
 */
public enum SecondaryCommand {

    BY("/by ", 4),
    AT("/at ", 4);

    private final String value; // Value provided by user.
    private final int length;   // Length of the string provided by the user

    /**
     * Constructs a Secondary Command enum.
     *
     * @param secondaryCommandString the value representing the string value of the enum.
     * @param secondaryCommandLength the length of the string representing the value of the enum.
     */
    SecondaryCommand(String secondaryCommandString, int secondaryCommandLength) {
        value = secondaryCommandString;
        length = secondaryCommandLength;
    }

    /**
     * Returns the value of the secondary command enum.
     *
     * @return the string representation of a secondary command enum.
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns the length of the secondary command enum.
     *
     * @return the integer value of the length of the secondary command enum.
     */
    public int getLength() {
        return length;
    }
}
