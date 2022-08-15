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
        final String markCommand = "mark";
        final String unmarkCommand = "unmark";

        String[] command = input.split(" ");

        switch (command[0]) {

            case exitCommand:
                sendMessage(exitMessage);
                return false;

            case displayListCommand:
                displayList(list);
                break;

            case markCommand:
                markItem(command[1]);
                break;

            case unmarkCommand:
                unmarkItem(command[1]);
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
        list.add(new Task(item));
        sendMessage("added: " + item);
    }

    /**
     * Attempt to mark an item as complete in the list, given a string index.
     * @param input String index. Will be parsed into int.
     */
    private static void markItem(String input) {
        int index = -1;
        try {
            index = parseInt(input);
        } catch (NumberFormatException e) {
            sendMessage(invalidIndexMessage);
            return;
        }
        markItem(index);
    }

    /**
     * Attempt to unmark an item as complete in the list, given a string index.
     * @param input String index. Will be parsed into int.
     */
    private static void unmarkItem(String input) {
        int index = -1;
        try {
            index = parseInt(input);
        } catch (NumberFormatException e) {
            sendMessage(invalidIndexMessage);
            return;
        }
        unmarkItem(index);
    }

    /**
     * Marks an item as complete in the list.
     * @param index Index to mark as complete, starting from 1.
     */
    private static void markItem(int index) {
        if (index < 1 || index > list.size() + 1) {
            sendMessage(invalidIndexMessage);
            return;
        }
        list.get(index - 1).complete = true;
        sendMessage("Nice! I've marked this task as done:\n" + list.get(index - 1));
    }

    /**
     * Marks an item as incomplete in the list.
     * @param index Index to mark as incomplete, starting from 1.
     */
    private static void unmarkItem(int index) {
        if (index < 1 || index > list.size() + 1) {
            sendMessage(invalidIndexMessage);
            return;
        }
        list.get(index - 1).complete = false;
        sendMessage("OK, I've marked this task as not done yet:\n" + list.get(index - 1));
    }

    /**
     * Displays the items in a string list, enumerated from 1.
     * @param list The list of strings to display.
     */
    private static void displayList(ArrayList<Task> list) {
        String output = "Here are the tasks in your list:";
        for (int i = 0; i < list.size(); i++) {
            output += "\n" + (i + 1) + ". " + list.get(i);
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

    /**
     * A class that represents a task in the task list.
     */
    private static class Task {
        public String text = "";
        public boolean complete = false;

        public Task(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return "[" + (complete ? "X" : " ") + "] " + text;
        }
    }
}
