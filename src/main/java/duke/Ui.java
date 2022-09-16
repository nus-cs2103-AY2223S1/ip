package duke;

/** A class that handles the user interface of the Duke bot. */
public class Ui {
    private String welcomeMessage;

    /**
     * Initialises the UI of the bot.
     */
    public Ui() {
        this.welcomeMessage = "Hello! I'm Duke!\nWhat can I do for you?\n";
    }

    /**
     * Runs the user interface of the bot.
     *
     * @return String representation of the welcome message.
     */
    public String run() {
        return this.welcomeMessage;
    }
}

