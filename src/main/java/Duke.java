import java.util.Scanner;

public class Duke {
    // List to store text entered by the user and display them back to the user when requested
    private static Task[] wordList = new Task[100];

    //Parameter to keep track of the number of items in the list
    private static int numOfItems = 0;

    /**
     * Start the program
     */
    private static void greet() {
        System.out.println("Hello I am\n" + Constants.LOGO);
        System.out.println("May I help you?");
    }

    /**
     * Repeat user input (except 'bye')
     * @param input the message that user type
     */
    private static void echo(String input) {
        System.out.println(Constants.ARROW + input);
    }

    /**
     * Exit when user type 'bye'
     */
    private static void exit() {
        System.out.println("Great that you joined. See you soon. Bye!");
    }

    /**
     * Add text that user typed to the word list
     * @param task text the user typed
     */
    private static void add(Task task) {
        System.out.println(Constants.ARROW + "Added task: " + task.toString());
        Duke.wordList[numOfItems] = task;
        numOfItems++;
        System.out.println("Now you have " + Duke.numOfItems + " task(s) on your list.");
    }

    /**
     * Print all item in the word list
     */
    private static void listItems() {
        System.out.println(Constants.LISTING_MESSAGE);
        for (int i = 0; i < numOfItems; i++) {
            System.out.println((i+1) + ") " + wordList[i].toString());
        }
    }

    public static void main(String[] args) {
        // Greeting
        Duke.greet();

        // User Input
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        // Echoing
        while (!userInput.equals(Constants.EXIT)) {
            String typeOfTask = userInput.split(" ")[0];
            int index;
            switch (typeOfTask) {
                case Constants.LIST:
                    Duke.listItems();
                    break;
                case Constants.UNMARK:
                    index =  Integer.parseInt(userInput.split(" ")[1]);
                    wordList[index-1].unmark();
                    break;
                case Constants.MARK:
                    index =  Integer.parseInt(userInput.split(" ")[1]);
                    wordList[index-1].markAsDone();
                    break;
                case Constants.TODO:
                    Duke.add(new ToDo(userInput.substring(5)));
                    break;
                case Constants.DEADLINE:
                    String[] deadline = userInput.substring(9).split(" /by ");
                    Duke.add(new Deadline(deadline[0], deadline[1]));
                    break;
                case Constants.EVENT:
                    String[] event = userInput.substring(6).split(" /at ");
                    Duke.add(new Event(event[0], event[1]));
                    break;
            }
            userInput = scanner.nextLine();
        }

        // Bye
        Duke.exit();
    }
}
