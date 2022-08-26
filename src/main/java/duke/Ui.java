package duke;

/**
 * A class that handles the user interface of the Duke bot.
 */
public class Ui {
    private String welcomeMessage;

    /**
     * A constructor for Ui object.
     */
    public Ui() {
        this.welcomeMessage = "Hello! I'm Duke\nWhat can I do for you?\n";
    }

    /**
     * Runs the Ui of the bot.
     */
    public void run() {
        System.out.println(this.welcomeMessage);
    }
}

