import java.util.Scanner;

public class Duke {
    public static final String GREETING_MESSAGE = "    ____________________________________________________________\n" +
            "     Hello! I'm Duke\n" +
            "     What can I do for you?\n" +
            "    ____________________________________________________________\n";
    public static final String GOODBYE_MESSAGE = ("    ____________________________________________________________\n" +
            "     Bye. Hope to see you again soon!\n" +
            "    ____________________________________________________________\n");

    public static void main(String[] args) {
        // Greeting message is always printed.
        System.out.println(GREETING_MESSAGE);

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        // Continuously repeat user's input until it matches "bye".
        while (!input.equals("bye")) {
            System.out.println(generateReply(input));
            input = in.nextLine();
        }

        // Goodbye message is always printed.
        System.out.println(GOODBYE_MESSAGE);
    }

    public static String generateReply(String userInput) {
        return String.format("    ____________________________________________________________\n" +
                "     %s\n" +
                "    ____________________________________________________________\n", userInput);
    }
}
