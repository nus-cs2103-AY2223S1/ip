package Duke.enums;

/**
 * SecondaryCommand enum represents the possible secondary commands that a user
 * can provide along with certain commands. The value of these commands are the
 * string the user requires inputting.
 */
public enum SecondaryCommand {

    BY("/by", 3),
    AT("/at", 3);

    private final String value; // Value provided by user.
    private final int length;   // Length of the string provided by the user

    /**
     * Constructor of a secondary command enum.
     *
     * @param value  Value provided by user.
     * @param length Length of the string provided by the user
     */
    SecondaryCommand(String value, int length) {
        this.value = value;
        this.length = length;
    }

    /**
     * @return the value of the secondary command.
     */
    public String getValue() {
        return value;
    }

    /**
     * @return the length of the secondary command.
     */
    public int getLength() {
        return length;
    }
}
