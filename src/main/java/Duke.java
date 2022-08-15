import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class Duke {

    /**
     * The greeting message used when main is loaded.
     */
    private static final String greetingMessage = "Hello! I'm Duke\nWhat can I do for you?";

    /**
     * The exit message used when the user issues the exit command.
     */
    private static final String exitMessage = "Bye. Hope to see you again soon!";

    /**
     * A list to contain the items given by the user.
     */
    private static ArrayList<String> list;

    /**
     * Runs the Duke chatbot.
     * @param args Input arguments.
     */
    public static void main(String[] args) {

        list = new ArrayList<>();

        sendMessage(greetingMessage);
        Scanner userInput = new Scanner(System.in);
        boolean receiveInput = true;

        while(receiveInput) {
            String input = userInput.nextLine();
            receiveInput = parseInput(input);
        }

    }

    /**
     * Parses the user's input.
     * @param input Input from the user.
     * @return Whether the program should continue receiving input.
     */
    private static boolean parseInput(String input) {

        final String exitCommand = "bye";
        final String displayListCommand = "list";

        switch (input) {

            case exitCommand:
                sendMessage(exitMessage);
                return false;

            case displayListCommand:
                displayList(list);
                break;

            default:
                addToList(input);
                break;

        }

        return true;
    }

    /**
     * Adds an item to the static list.
     * @param item Item to add to the list.
     */
    private static void addToList(String item) {
        list.add(item);
        sendMessage("added: " + item);
    }

    /**
     * Displays the items in a string list, enumerated from 1.
     * @param list The list of strings to display.
     */
    private static void displayList(ArrayList<String> list) {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            output += i == 0
                ? (i + 1) + ". " + list.get(i)
                : "\n" + (i + 1) + ". " + list.get(i);
        }
        sendMessage(output);
    }

    /**
     * Prints a message into the line with the relevant formatting.
     * @param message Message to print.
     */
    private static void sendMessage(String message) {

        String indentation = "    ";
        String line = "____________________________________________________________";
        Stream<String> messageLines = message.lines();

        System.out.println(indentation + line);
        messageLines.forEach(x -> System.out.println(indentation + x));
        System.out.println(indentation + line);
    }
}
