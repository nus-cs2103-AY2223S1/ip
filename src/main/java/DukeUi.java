import java.util.Scanner;

/**
 * DukeUi deals with interactions with the user
 * Based on the user input it recieves, it passes it to other classes to handle the inputs
 */
public class DukeUi {
    private static final String LINE = "____________________________________________________________";
    private static final String START_MESSAGE = " Hello! I'm Duke\n" + " What can I do for you?";
    private static final String END_MESSAGE = " Bye. Hope to see you again soon!";

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String userCommand = scanner.next();
        String userAction = scanner.nextLine().stripLeading();
        return userCommand + "_______________" + userAction;
    }

    public static void sendMessage(String message) {
        System.out.println(message);
    }

    public static void showWelcome() {
        sendMessage(LINE);
        sendMessage(START_MESSAGE);
        sendMessage(LINE);
    }

    public void showLoadingError() {
        sendMessage("File not found, creating new file in current directory");
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void endMessage() {
        sendMessage(END_MESSAGE);
    }
}
