import java.util.Scanner;

public class Duke {

    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static String greeting = "Hello, I'm\n" + logo + "How can I help you today?";

    private static String farewell = "Goodbye! Hope to see you again!";

    public static void main(String[] args) {
        generateMessage(greeting);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                generateMessage(farewell);
                break;
            }
            generateMessage(input);
        }
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void generateMessage(String message) {
        printLine();
        System.out.println("Duke \uD83D\uDE0E says: ");
        System.out.println(message);
        printLine();
    }

}
