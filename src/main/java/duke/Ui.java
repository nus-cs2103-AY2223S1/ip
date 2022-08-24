package duke;

import java.util.Scanner;

public class Ui {
    private static final String line = "____________________________________________________________";
    private static final String indentedLine = "     " + line;
    static final String initialMessage = indentedMessage(
            "\n      Hello! I'm Duke\n      What can I do for you?\n");
    //this is only public for now, need to refactor Parser into Command classes and call this from Command instead
    public static String indentedMessage(String message) {
        return indentedLine + message + indentedLine;
    }
    private static final String byeMessage = indentedMessage("\n      Bye. This doesn't have to be the end!\n");


    private final Scanner userScanner;

    public Ui() {
        this.userScanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(initialMessage);
    }

    public String getNextCommand() {
        return userScanner.nextLine();
    }

    public void showLine() {
        System.out.println(line);
    }

    public void showError(String errorMessage) {
        System.out.println(indentedMessage(errorMessage));
    }

    public void showExitMessage() {
        System.out.println(byeMessage);
    }
}
