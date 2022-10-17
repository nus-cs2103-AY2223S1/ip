package hazell.ui;

import hazell.Hazell;

/**
 * An interface for Ui objects, e.g. GUI, CLI.
 */
public interface UiInterface {

    /**
     * Attaches a reference to the bot object.
     *
     * @param hazell Bot
     */
    void attachBotInstance(Hazell hazell);

    /**
     * Returns whether this Ui object contains user input that can be read from.
     *
     * @return boolean, as specified above
     */
    boolean hasNextUserInput();

    /**
     * Gets the next user input from the object's internal buffer.
     *
     * @return The user input
     */
    String getNextUserInput();

    /**
     * Shows the text that the user has inputted.
     *
     * @param input The message that the user inputted.
     */
    void displayUserInput(String input);

    /**
     * Replies user in a formatted way.
     *
     * @param response The message to be pretty-printed to user
     */
    void displayBotResponse(String response);

    /**
     * Makes this Ui object take control of the entire program execution.
     */
    void run();


    /**
     * Does initial tasks, e.g. print welcome messages.
     */
    void start();
}
