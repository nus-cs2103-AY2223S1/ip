package ui;

public class Duke {
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        if (input.trim().equals("bye")) {
            return "Exiting...";
        }
        return "Duke heard: " + input;
    }
}
