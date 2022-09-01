package duke;

/**
 * Encapsulation of interactions with the user.
 *
 * @author Sun Ruoxin
 */
public class Ui {
    /**
     * The logo of the chatbot.
     */
    protected String logo = "     _   _    ______     _____ ____\n"
            + "    | | / \\  |  _ \\ \\   / /_ _/ ___|\n"
            + " _  | |/ _ \\ | |_) \\ \\ / / | |\\___ \\\n"
            + "| |_| / ___ \\|  _ < \\ V /  | | ___) |\n"
            + " \\___/_/   \\_\\_| \\_\\ \\_/  |___|____/\n";

    /**
     * Gives feedback to the user when an error is encountered while loading the file.
     */
    public void showLoadingError() {
        say("Encountered error while loading.", true, true);
    }

    /**
     * Gives feedback to the user when an error is encountered.
     *
     * @param errorMessage the message of the error
     */
    public void showError(String errorMessage) {
        say("Encountered error: " + errorMessage, true, true);
    }

    /**
     * Prints out the message in a fixed format.
     *
     * @param message the message to be printed out
     * @param isFirstLine whether the message is the first line
     * @param isLastLine whether the message is the last line
     */
    public void say(String message, boolean isFirstLine, boolean isLastLine) {
        String line = "____________________________________________________________";
        if (isFirstLine) {
            System.out.println(line);
        }
        System.out.println(" " + message);
        if (isLastLine) {
            System.out.println(line);
        }
    }
}
