package wanya.ui;

/**
 * Represents user interface to deal with interactions from user.
 */
public class Ui {
    /**
     * Displays the greeting message.
     *
     * @return String greeting message.
     */
    public static String greet() {
        String startMsg = "Hello!!! My name is Wanya! WWaku WWaku!\n"
                + "How can I help you?";
        return startMsg;
    }

    /**
     * Displays the closing message.
     *
     * @return String closing message.
     */
    public static String exit() {
        String closeMsg = "Yayyy Wanya gets to slack and watch shows now. Bye bye! :)";
        return closeMsg;
    }

    /**
     * Displays the error message to user.
     *
     * @param e Exception thrown.
     * @return Error message.
     */
    public String showErrorMessage(Exception e) {
        return e.getMessage();
    }

    /**
     * Displays the message for user to enter valid date time format.
     *
     * @param taskType the initial of the task.
     * @return String message to inform user of valid date time format.
     */
    public static String showDateTimeFormat(String taskType) {
        String preposition = taskType.equals("E") ? "/at" : "/by";
        String dateTimeErrorMsg = ("Please enter a valid date behind " + preposition + " with the format "
                + "\"yyyy-mm-dd HH:mm\" where time is optional.\n "
                + "If time is provided, leave it in 24 hours format.");
        return dateTimeErrorMsg;
    }

    /**
     * Displays that bot is starting up.
     *
     * @return String loading message.
     */
    public static String showLoading() {
        String loadingMessage = "....Loa....Loading....Please wait....";
       return loadingMessage;
    }
}
