package duke.gui;

/**
 * Stores the format for a better viewing experience in Command Line Interface.
 */
public class Ui {
    private String horizontalLine = "____________________________________________________________\n";

    /**
     * Prints the logo of Duke.
     */
    public void welcomeLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints the input in between 2 lines for better view.
     * @param replyToBePrinted the String which will be printed
     */
    public void reply(String replyToBePrinted) {
        System.out.print(horizontalLine + replyToBePrinted + '\n' + horizontalLine);
    }

    /**
     * Prints a file loading error message.
     */
    public void showLoadingError() {
        reply("File not found. Creating new file...");
    }

}
