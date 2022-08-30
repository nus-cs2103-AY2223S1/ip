package duke;

/**
 * Entry point of the Duke chatbot.
 * Starts the application and by initializing the driver class Dukebot
 * and handling passing input to it.
 */
public class Duke {

    public static void main(String[] args) {
        TextUi ui = new TextUi();
        Dukebot driver = new Dukebot(ui);
        driver.run();
    }
}
