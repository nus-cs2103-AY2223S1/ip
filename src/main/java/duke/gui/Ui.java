package duke.gui;

/**
 * Stores the format for a better viewing experience in Command Line Interface.
 */
public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";

    /**
     * Prints the input in between 2 lines for better view.
     * @param replyToBePrinted the String which will be printed
     */
    public void reply(String replyToBePrinted) {
        System.out.print(HORIZONTAL_LINE + replyToBePrinted + '\n' + HORIZONTAL_LINE);
    }

    /**
     * Prints a file loading error message.
     */
    public void showLoadingError() {
        reply("File not found. Creating new file...");
    }

}
