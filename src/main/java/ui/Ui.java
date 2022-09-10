package ui;

/**
 * Ui has all the methods and logic that interacts directly with the user through
 * the console.
 */
public class Ui {

    protected String fredMessage;

    /**
     * Show loading error from start of chat bot.
     */
    public void showLoadingError() {
        System.out.println("Loading error!");
    }

    /**
     * Store message that Fred is supposed to show to the user.
     * @param message
     */
    public void storeMessage(String message) {
        fredMessage = message;
    }

    /**
     * Return the message that Fred is supposed to show to the user.
     * @return String containing message to be shown to the user
     */
    public String getMessage() {
        return fredMessage;
    }
}
