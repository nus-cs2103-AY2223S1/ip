import java.util.Scanner;

public class Duke {
    private static boolean isAcceptingInput;

    private static void printMessage(String msg) {
        String border = "____________________________________________________________\n";
        System.out.println(border + msg + "\n" + border);
    }

    private static void printStartupMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String startupMsg = "Hello! I'm Duke\n" + "What can I do for you?";
        printMessage(startupMsg);
    }

    private static void exit() {
        isAcceptingInput = false;
        String exitMsg = "Bye. Hope to see you again soon!";
        printMessage(exitMsg);
    }

    private static void echo(String input) {
        printMessage(input);
    }

    private static void processInput(String input) {
        switch (input) {
            case "bye":
                exit();
                break;
            default:
                echo(input);
        }
    }

    private static void startDuke() {
        isAcceptingInput = true;
        printStartupMessage();
        Scanner sc = new Scanner(System.in);

        while (isAcceptingInput) {
            String input = sc.nextLine();
            processInput(input);
        }
    }

    public static void main(String[] args) {
        startDuke();
    }
}
