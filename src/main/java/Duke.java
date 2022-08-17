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

    // Keyword to request stored items
    private static final String LIST = "list";

    // List to store text entered by the user and display them back to the user when requested
    private static Task[] wordList = new Task[100];

    //Parameter to keep track of the number of items in the list
    private static int numOfItems = 0;

    /**
     * Start the program
     */
    private static void greet() {
        System.out.println("Hello I am\n" + Duke.LOGO);
        System.out.println("May I help you?");
    }

    /**
     * Repeat user input (except 'bye')
     * @param input the message that user type
     */
    private static void echo(String input) {
        System.out.println(Duke.ARROW + input);
    }

    /**
     * Exit when user type 'bye'
     */
    private static void exit() {
        System.out.println("Great that you joined. See you soon. Bye!");
    }

    /**
     * Add text that user typed to the word list
     * @param message text the user typed
     */
    private static void add(String message) {
        Duke.echo("Added item: " + message);
        Duke.wordList[numOfItems] = new Task(message);
        numOfItems++;
    }

    /**
     * Print all item in the word list
     */
    private static void listItems() {
        for (int i = 0; i < numOfItems; i++) {
            System.out.println((i+1) + ") " + wordList[i].getStatusIcon() + wordList[i].getDetail());
        }
    }

    public static void main(String[] args) {
        // Greeting
        Duke.greet();

        // User Input
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        // Echoing
        while (!userInput.equals(Duke.EXIT)) {
            if (userInput.equals(Duke.LIST)) {
                Duke.listItems();
            } else if (userInput.contains("unmark")){
                int index =  Integer.parseInt(userInput.split(" ")[1]);
                wordList[index-1].unmark();
            } else if (userInput.contains("mark")){
                int index =  Integer.parseInt(userInput.split(" ")[1]);
                wordList[index-1].markAsDone();
            } else {
                Duke.add(userInput);
            }
            userInput = scanner.nextLine();
        }

        // Bye
        Duke.exit();
    }
}
