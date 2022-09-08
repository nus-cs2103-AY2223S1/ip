package duke;

/**
 * Main Duke function.
 */
public class Duke {

    /**
     * Generate a response to user input.
     *
     * @param input returns a string of user input
     */
    public String getResponse(String input) {
        try {
            return Parser.parseInput(input);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
