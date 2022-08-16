import java.util.Scanner;

public class Duke {
    // Name of the chatbot
    private static final String NAME = "Duke";
    //  Horizontal line separator used in beautifying print commands
    private static final String SEPARATOR = "____________________________________________________________";
    // The 'bye' command entered by the user is used to indicate to the program to exit
    private static final String EXIT_COMMAND = "bye";

    /**
     * Greet the user and introduce itself
     */
    private static void greet() {
        System.out.println(Duke.SEPARATOR);
        System.out.printf("Hello! I'm %s\nWhat can I do for you?\n", Duke.NAME);
        System.out.println(Duke.SEPARATOR + "\n");
    }

    /**
     * Prints the input string that was received using the separator and tab formatter
     *
     * @param command Input string that was received
     */
    private static void echo(String command) {
        System.out.println("\t" + Duke.SEPARATOR);
        System.out.println("\t" + command);
        System.out.println("\t" + Duke.SEPARATOR + "\n");
    }

    /**
     * Provide a terminating goodbye message to the user
     */
    private static void quit() {
        Duke.echo("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Greet the user
        Duke.greet();

        // The command entered by the user
        String command = scanner.nextLine();
        while (!command.equals(Duke.EXIT_COMMAND)) {
            Duke.echo(command);
            command = scanner.nextLine();
        }

        // Bid the user goodbye
        Duke.quit();
    }
}
