package duke.core;

import java.util.Scanner;
import java.util.stream.Stream;

public class Ui {

    /**
     * The greeting message used when main is loaded.
     */
    private static final String welcomeMessage = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String indentation = "    ";
    private static final String line = "____________________________________________________________";

    private boolean isExit = false;
    private Scanner userInput = new Scanner(System.in);

    public String readCommand() {
        return userInput.nextLine();
    }

    public void showWelcome()
    {
        showLine();
        showMessage(welcomeMessage);
        showLine();
    }

    public void showLine()
    {
        showMessage(line);
    }

    public void showError(DukeException e) {
        showMessage(e.getMessage());
    }

    public void showMessage(String message) {
        Stream<String> messageLines = message.lines();
        messageLines.forEach(x -> System.out.println(indentation + x));
    }

    public void setIsExit(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return isExit;
    }

}
