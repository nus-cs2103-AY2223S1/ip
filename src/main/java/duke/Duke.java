package duke;

/**
 * Main Duke function.
 */
public class Duke {

    public void run() {
        UI.start();
        Parser.parseInput();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    //todo
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

}
