package duke;

/**
 * Main Duke function.
 */
public class Duke {

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            return Parser.parseInput(input);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

}
