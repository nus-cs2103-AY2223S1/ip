package hazell.ui;

import hazell.Hazell;

public interface UiInterface {

    /**
     * Attach a reference to the bot object.
     * @param hazell Bot
     */
    void attachBotInstance(Hazell hazell);

    boolean hasNextUserInput();

    /**
     * Get the next user input from the object's internal buffer.
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
     * Make this Ui object take control of the entire program execution.
     */
    void run();


    /**
     * Do initial tasks, e.g. print welcome messages.
     */
    void start();
}