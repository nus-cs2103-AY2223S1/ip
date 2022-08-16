import java.util.Scanner;

public class Duke {
    // Name of the chatbot
    private static final String NAME = "Duke";
    //  Horizontal line separator used in beautifying print commands
    private static final String SEPARATOR = "____________________________________________________________";
    // The 'bye' command entered by the user is used to indicate to the program to exit
    private static final String EXIT_COMMAND = "bye";

    // The greeting message used by the chatbot when the program starts
    private static final String GREETING_MESSAGE = String.format("Hello! I'm %s\n\tWhat can I do for you?", Duke.NAME);
    // The goodbye message used by the chatbot when the program terminates
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    /**
     * Returns the formatted input string that was received using the separator and tab formatter
     *
     * @param input Input string that was received
     * @return The formatted output string
     */
    private static String outputFormatter(String input) {
        String lineSeparator = String.format("\t%s\n", Duke.SEPARATOR);
        return String.format("%s\t%s\n%s", lineSeparator, input, lineSeparator);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Greet the user
        System.out.println(Duke.outputFormatter(Duke.GREETING_MESSAGE));

        // Receive the command entered by the user
        String command = scanner.nextLine();
        while (!command.equals(Duke.EXIT_COMMAND)) {
            // Echo the command entered by the user
            System.out.println(Duke.outputFormatter(command));
            // Continue to retrieve the next command
            command = scanner.nextLine();
        }

        // Bid the user goodbye
        System.out.println(Duke.outputFormatter(Duke.GOODBYE_MESSAGE));
    }
}
