import java.util.Scanner;
/**
 * Duke is a personal assistant chatbot.
 * It helps a person to keep track of various things.
 *
 * @author Rexong
 */
public class Duke {
    private static final String INTRODUCE_MESSAGE = "Hello! I'm Duke";
    private static final String ASK_MESSAGE = "What can I do for you?";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final int TASK_SIZE = 100;
    private int taskIndex = 0;
    private String[] tasks = new String[TASK_SIZE];


    /**
     * Print messages passed into the parameter.
     *
     * @param message Message to be logged
     */
    private void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Print both introduce and ask messages.
     */
    public void printGreetMessage() {
        printMessage(INTRODUCE_MESSAGE);
        printMessage(ASK_MESSAGE);
    }

    /**
     * Print exit message.
     */
    public void printExitMessage() {
        printMessage(EXIT_MESSAGE);
    }

    /**
     * Echos commands entered by user.
     *
     * @param message Message entered by the user to be echoed
     */
    public void echoMessage(String message) {
        printMessage(message);
    }



    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner scanner = new Scanner(System.in);

        duke.printGreetMessage();
        String command = scanner.nextLine();
        while (!command.equals("bye")) {
            duke.echoMessage(command);
            command = scanner.nextLine();
        }
        duke.printExitMessage();
    }
}
