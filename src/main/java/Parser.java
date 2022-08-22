public class Parser {
    private static final String invalidTaskIndexMessage = "You entered an invalid task index";

    private final String direction;
    private final String meta;

    public Parser(String cmd) {
        String[] tokens = cmd.split("\\s+", 2);
        this.direction = tokens[0];
        this.meta = tokens.length > 1 ? tokens[1] : null;
    }

    public int extractIndex() throws DukeException {
        try {
            return Integer.parseInt(this.meta.replaceAll("[^0-9]", ""));
        } catch (NumberFormatException e) {
            throw new DukeException(invalidTaskIndexMessage);
        }
    }

    public String getDirection() {
        return this.direction;
    }

    public String getMeta() {
        return this.meta;
    }
}