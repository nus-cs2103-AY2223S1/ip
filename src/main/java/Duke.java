import java.util.Scanner;

public class Duke {
    private static final String LINE = "    ____________________________________________________________\n";
    private static final String INDENTATION = "     ";

    private static boolean isDone = false;

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);

        while (!isDone) {
            String command = sc.nextLine();

            switch (command) {
                case "bye":
                    bye();
                    break;
                default:
                    printMessage(command);
            }
        }
    }

    private static void exitProgram() {
        isDone = true;
    }

    private static void printMessage(String message) {
        System.out.println(LINE +
                message + "\n" +
                LINE);
    }

    private static void greet() {
        String greetingMessage = INDENTATION + "Hello! I'm Duke\n" +
                INDENTATION + "What can I do for you?\n";

        printMessage(greetingMessage);
    }

    private static void bye() {
        String byeMessage = INDENTATION + "Bye. Hope to see you again soon!\n";
        printMessage(byeMessage);
        exitProgram();
    }
}
