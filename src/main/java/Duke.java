import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

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
     * A message to display when a given index is invalid.
     */
    private static final String invalidIndexMessage = "Index is invalid!";

    /**
     * A list to contain the items given by the user.
     */
    private static ArrayList<Task> list;

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
        final String toDoCommand = "todo";
        final String deadlineCommand = "deadline";
        final String eventCommand = "event";
        final String markCommand = "mark";
        final String unmarkCommand = "unmark";

        String[] command = input.split(" ", 2);

        switch (command[0]) {

            case exitCommand:
                sendMessage(exitMessage);
                return false;

            case displayListCommand:
                displayList(list);
                break;

            case toDoCommand:
                parseToDoCommand(command[1]);
                break;

            case deadlineCommand:
                parseDeadlineCommand(command[1]);
                break;

            case eventCommand:
                parseEventCommand(command[1]);
                break;

            case markCommand:
                parseMarkCommand(command[1]);
                break;

            case unmarkCommand:
                parseUnmarkCommand(command[1]);
                break;

            default:
                break;

        }

        return true;
    }

    /**
     * Adds an item to the static list.
     * @param item Item to add to the list.
     */
    private static void addToList(Task item) {
        list.add(item);
        sendMessage("Got it. I've added this task:\n  "
                + item + "\nNow you have " + list.size() + " tasks in the list.");
    }

    /**
     * Parses the user's input for a todo command.
     * @param input User input after the todo command.
     */
    private static void parseToDoCommand(String input) {
        addToList(new ToDo(input));
    }

    /**
     * Parses the user's input for a deadline command.
     * @param input User input after the deadline command.
     */
    private static void parseDeadlineCommand(String input) {
        String[] command = input.split(" /by ", 2);

        if (command.length < 2) {
            sendMessage("No deadline given!");
            return;
        }

        addToList(new Deadline(command[0], command[1]));
    }

    /**
     * Parses the user's input for an event command.
     * @param input User input after the event command.
     */
    private static void parseEventCommand(String input) {
        String[] command = input.split(" /at ", 2);

        if (command.length < 2) {
            sendMessage("No time given!");
            return;
        }

        addToList(new Event(command[0], command[1]));
    }

    /**
     * Attempt to mark an item as complete in the list, given a string index.
     * @param input String index. Will be parsed into int.
     */
    private static void parseMarkCommand(String input) {

        int index;
        try {
            index = parseInt(input);
        } catch (NumberFormatException e) {
            sendMessage(invalidIndexMessage);
            return;
        }

        if (index < 1 || index > list.size() + 1) {
            sendMessage(invalidIndexMessage);
            return;
        }
        list.get(index - 1).setComplete(true);
        sendMessage("Nice! I've marked this task as done:\n" + list.get(index - 1));

    }

    /**
     * Attempt to unmark an item as complete in the list, given a string index.
     * @param input String index. Will be parsed into int.
     */
    private static void parseUnmarkCommand(String input) {
        int index;
        try {
            index = parseInt(input);
        } catch (NumberFormatException e) {
            sendMessage(invalidIndexMessage);
            return;
        }

        if (index < 1 || index > list.size() + 1) {
            sendMessage(invalidIndexMessage);
            return;
        }
        list.get(index - 1).setComplete(false);
        sendMessage("OK, I've marked this task as not done yet:\n" + list.get(index - 1));

    }


    /**
     * Displays the items in a string list, enumerated from 1.
     * @param list The list of strings to display.
     */
    private static void displayList(ArrayList<Task> list) {
        StringBuilder output = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            output.append("\n").append(i + 1).append(". ").append(list.get(i));
        }
        sendMessage(output.toString());
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
