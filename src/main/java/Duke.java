import java.util.Scanner;

public class Duke {
    // Logo
    private static final String LOGO =
            "   _____    _____   ___    __    ___    ____    _______ \n" +
            "  / ____|  / ____| |__ \\  /_ |  / _ \\  |___ \\  |__   __|\n" +
            " | |      | (___      ) |  | | | | | |   __) |    | |   \n" +
            " | |       \\___ \\    / /   | | | | | |  |__ <     | |   \n" +
            " | |____   ____) |  / /_   | | | |_| |  ___) |    | |   \n" +
            "  \\_____| |_____/  |____|  |_|  \\___/  |____/     |_|   \n";

    // Arrow to start the answer
    private static final String ARROW = "--> ";

    // Keyword to quit the program
    private static final String EXIT = "bye";

    /**
     * Method to start the program.
     */
    private static void greet() {
        System.out.println("Hello I am\n" + Duke.LOGO);
        System.out.println("May I help you?");
    }

    /**
     * Method to echo user input (except 'bye').
     * @param input the message that user type
     */
    private static void echo(String input) {
        System.out.println(Duke.ARROW + input);
    }

    /**
     * Method to exit when user type 'bye'.
     */
    private static void exit() {
        System.out.println("Great that you joined. See you soon. Bye!");
    }

    public static void main(String[] args) {
        // Greeting
        Duke.greet();

        // User Input
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        // Echoing
        while (!userInput.equals(Duke.EXIT)) {
            Duke.echo(userInput);
            userInput = scanner.nextLine();
        }

        // Bye
        Duke.exit();
    }
}
