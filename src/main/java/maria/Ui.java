package maria;

import java.util.Scanner;

/**
 * Represents the object for the user to interact with the chatbot.
 */
public class Ui {

    private Scanner scanner;

    /**
     * Creates a user interface object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the text to the user.
     * @param text The text to be shown
     */
    public void showText(String text) {
        System.out.println(text);
    }

    /**
     * Displays the instructions for the user to know how to use the chatbot.
     */
    public void showInstructions() {

        System.out.println("==========================");
        System.out.println("Hey, I am Maria. I can manage your tasks for you"); // Welcome text
        System.out.println("Type 'todo <name> <done (true/false)>' to create a todo.");
        System.out.println("Type 'deadline <name> <done (true/false)> <deadline (YYYY-MM-DD)>' to create a task with deadline.");
        System.out.println("Type 'event <name> <done (true/false)> <start (YYYY-MM-DD)> <end (YYYY-MM-DD)>' to create an event with a start and end time.");
        System.out.println("Type 'list' to list out all your tasks.");
        System.out.println("Type 'mark <index>' to complete a task.");
        System.out.println("Type 'unmark <index>' to un-complete a task.");
        System.out.println("Type 'delete <index>' to remove a task.");
        System.out.println("Type 'find <search_string>' to find a task.");
        System.out.println("Type 'bye' to finish the conversation.");
        System.out.println("==========================");

    }

    /**
     * Displays a new line.
     */
    public void showNewLine() {
        System.out.println();
    }

    /**
     * Displays a divider.
     */
    public void showDivider() {
        System.out.println("=========================");
    }

    /**
     * Prompts the user to give Maria a command, and returns the command string.
     * @return The command string
     */
    public String readCommand() {
        System.out.print("Type in your command for Maria: ");
        return this.scanner.nextLine();
    }

}
