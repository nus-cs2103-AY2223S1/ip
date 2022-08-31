package duke.ui;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;

import duke.DukeException;

/**
 * This class encapsulates I/O logic.
 */
public class Ui {

    private static final String GREETING_MESSAGE = "Hello! I'm TedBot ヾ(≧▽≦*)o\n"
            + "What do you want to do today?";
    private static final String GOODBYE_MESSAGE = "Bye! Hope to see you soon ༼- つ ◕_◕ ༽つ";
    private static final String STORAGE_LOADING_MESSAGE = "Loading save file.....";
    private static final String REPLY_HEADER = ">>";
    private static final String EXCEPTION_HEADER = "!>>";

    private final Scanner sysInScanner;
    private final PrintWriter printWriter;

    /**
     * Construtor of Ui. Constructed Ui will print to System.out.
     */
    public Ui() {
        sysInScanner = new Scanner(System.in);
        printWriter = new PrintWriter(System.out);
    }

    /**
     * Constructs UI that will print to the provided Writer.
     * @param writer the provided Writer.
     */
    public Ui(Writer writer) {
        sysInScanner = new Scanner(System.in);
        printWriter = new PrintWriter(writer);
    }

    private void outputString(String out, String header) {
        String[] splitOut = out.split("\n");
        printWriter.print(header + " ");
        printWriter.println(splitOut[0]);
        for (int i = 1; i < splitOut.length; i++) {
            for (int j = 0; j < header.length() + 1; j++) {
                printWriter.print(" ");
            }
            printWriter.println(splitOut[i]);
        }
        printWriter.flush();
    }

    /**
     * Displays specified reply to user.
     *
     * @param reply String to be displayed.
     */
    public void showReply(String reply) {
        outputString(reply, REPLY_HEADER);
    }

    /**
     * Displays specified DukeException to user.
     *
     * @param e DukeException to be displayed.
     */
    public void showException(DukeException e) {
        outputString("Uh-oh ☹! " + e.getMessage(), EXCEPTION_HEADER);
    }

    /**
     * Displays specified DukeException to user, along with the cause of the Exception
     * if and only if shouldShowCause is true.
     *
     * @param e Exception to be displayed.
     * @param shouldShowCause true if and only if the cause is to be shown.
     */
    public void showException(DukeException e, boolean shouldShowCause) {
        if (shouldShowCause) {
            outputString("Uh-oh ☹! " + e.getMessage() + "\nCause: " + e.getCause(), EXCEPTION_HEADER);
        } else {
            outputString("Uh-oh ☹! " + e.getMessage(), EXCEPTION_HEADER);
        }
    }

    /**
     * Displays specified Exception to user.
     *
     * @param e Exception to be displayed.
     */
    public void showException(Exception e) {
        outputString(String.format("Woah 😲! Undocumented exception encountered:\n"
                        + "%s\n"
                        + "Please let us know on our GitHub Issues along with the steps to recreate this exception.",
                e.toString()), EXCEPTION_HEADER);
    }

    /**
     * Displays the seperator to indicate the end of an interaction.
     */
    public void showSeperator() {
        System.out.println();
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        this.showReply(Ui.GREETING_MESSAGE);
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodbye() {
        this.showReply(Ui.GOODBYE_MESSAGE);
    }

    /**
     * Displays the loading message, to be used
     * when Storage is loading files.
     */
    public void showStorageLoadingMessage() {
        this.showReply(Ui.STORAGE_LOADING_MESSAGE);
    }

    /**
     * Reads a Command String from the user.
     *
     * @return the command String
     */
    public String readCommand() {
        printWriter.print("<< ");
        printWriter.flush();
        return sysInScanner.nextLine();
    }


}
