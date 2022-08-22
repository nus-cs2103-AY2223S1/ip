package duke;

public class Parser {
    private static final String invalidTaskIndexMessage = "You entered an invalid task index";

    private final String direction;
    private final String meta;

    public Parser(String cmd) {
        String[] tokens = cmd.split("\\s+", 2);
        this.direction = tokens[0];
        this.meta = tokens.length > 1 ? tokens[1] : null;
    }

    /**
     * Returns the integer index from the meta property.
     * Strips non-numeric characters and casts value to int.
     *
     * @return Integer index from the command
     * @throws DukeException If meta does not contain an integer.
     */
    public int extractIndex() throws DukeException {
        try {
            return Integer.parseInt(this.meta.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            throw new DukeException(invalidTaskIndexMessage);
        }
    }

    /**
     * Returns the direction part of the command.
     * The direction is the phrase that defines what the command does, e.g. todo, bye, etc.
     *
     * @return Direction of command.
     */
    public String getDirection() {
        return this.direction;
    }

    /**
     * Returns the meta part of the command.
     * The meta is the portion of the command that contains the description, date, index, etc.
     *
     * @return Meta of command.
     */
    public String getMeta() {
        return this.meta;
    }
}