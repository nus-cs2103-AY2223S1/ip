package duke;

import java.util.Scanner;

public class Ui {
    private static final String line = "____________________________________________________________\n";
    private static final String indentedLine = "     " + line;
    static final String initialMessage = indentedLine + indentedMessage(" Hello! I'm Duke\n      What can I do"
            + " for you?\n" + indentedLine);
    //this is only public for now, need to refactor Parser into Command classes and call this from Command instead
    public static String indentedMessage(String message) {
        return "     " + message;
    }
    private static final String byeMessage = ("      Bye. This doesn't have to be the end!\n");

    private final Scanner userScanner;

    public Ui() {
        this.userScanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.print(initialMessage);
    }

    public String getNextCommand() {
        return userScanner.nextLine();
    }

    public void showLine() {
        System.out.print(indentedLine);
    }

    public void showError(String errorMessage) {
        System.out.print(indentedMessage(errorMessage));
    }

    public void showExitMessage() {
        System.out.print(byeMessage);
    }
}
