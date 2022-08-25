package Duke.enums;

/**
 * Secondary commands that can be used by users in the duke application.
 */
public enum SecondaryCommand {

    BY("/by", 3),
    AT("/at", 3);

    private final String value;
    private final int length;

    /**
     * The constructor for the Secondary Command enum.
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
