package duke.ui;

import duke.DukeException;

import java.util.Scanner;

public class Ui {

    private static final String GREETING_MESSAGE = "Hello! I'm TedBot ヾ(≧▽≦*)o\n"
            + "What do you want to do today?";
    private static final String GOODBYE_MESSAGE = "Bye! Hope to see you soon ༼- つ ◕_◕ ༽つ";
    private static final String STORAGE_LOADING_MESSAGE = "Loading save file.....";
    private static final String REPLY_HEADER = ">>";
    private static final String EXCEPTION_HEADER = "!>>";

    private final Scanner sysInScanner;

    public Ui() {
        sysInScanner = new Scanner(System.in);
    }

    private void outputString(String out, String header) {
        String[] splitOut = out.split("\n");
        System.out.print(header + " ");
        System.out.println(splitOut[0]);
        for (int i = 1; i < splitOut.length; i++) {
            for (int j = 0; j < header.length() + 1; j++) System.out.print(" ");
            System.out.println(splitOut[i]);
        }
    }

    public void showReply(String reply) {
        outputString(reply, REPLY_HEADER);
    }

    public void showException(DukeException e) {
        outputString("Uh-oh ☹! " + e.getMessage(), EXCEPTION_HEADER);
    }

    public void showException(DukeException e, boolean shouldShowCause) {
        if (shouldShowCause) {
            outputString("Uh-oh ☹! " + e.getMessage() + "\nCause: " + e.getCause(), EXCEPTION_HEADER);
        } else {
            outputString("Uh-oh ☹! " + e.getMessage(), EXCEPTION_HEADER);
        }
    }

    public void showException(Exception e) {
        outputString(String.format("Woah 😲! Undocumented exception encountered:\n"
                        + "%s\n"
                        + "Please let us know on our GitHub Issues along with the steps to recreate this exception.",
                e.toString()), EXCEPTION_HEADER);
    }

    public void showSeperator() {
        System.out.println();
    }

    public void showWelcome() {
        this.showReply(Ui.GREETING_MESSAGE);
    }

    public void showGoodbye() {
        this.showReply(Ui.GOODBYE_MESSAGE);
    }

    public void showStorageLoadingMessage() {
        this.showReply(Ui.STORAGE_LOADING_MESSAGE);
    }

    public String readCommand() {
        System.out.print("<< ");
        return sysInScanner.nextLine();
    }


}
