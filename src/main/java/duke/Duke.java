package duke;

/**
 * Entry point of the Duke chatbot.
 * Starts the application and by initializing the driver class Dukebot
 * and handling passing input to it.
 */
public class Duke {

    public static void main(String[] args) {
        Ui ui = new Ui();
        Dukebot driver = new Dukebot(ui);
        while (true) {
            String input = ui.getInput();
            driver.handleInput(input);
        }
    }
}
