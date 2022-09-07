package duke.ui;

import java.util.Scanner;

import duke.data.TaskList;

/**
 * Prints messages to the console and reads in commands from the user.
 */
public class Ui {

    /** Scanner object to read in user input */
    private Scanner sc;


    /**
     * Creates a new Ui object.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }


    /**
     * Returns the user's command read from standard input.
     *
     * @return String containing the user's command.
     */
    public String readCommand() {
        return sc.nextLine();
    }


    /**
     * Closes the Scanner object to stop reading user input.
     */
    public void stopReadingUserInput() {
        sc.close();
    }


    /**
     * Returns a String listing the Tasks in the specified TaskList object line by line.
     *
     * @param tasks TaskList object to print.
     * @param isSearchResult boolean value to indicate whether the TaskList object was from a search result.
     * @return String listing all Task objects line by line.
     */
    public String listTasks(TaskList tasks, boolean isSearchResult) {

        // Modify the header sentence based on whether the specified TaskList is from a search result
        String result = isSearchResult ? "Here are the matching tasks in your list:\n"
                                        : "Here are the tasks in your list:\n";

        for (int i = 0; i < tasks.getSize(); i++) {
            String line = String.format("%d. %s\n", i + 1, tasks.getTask(i));
            result = result.concat(line);
        }

        return result;
    }


    /**
     * Prints the specified message to the console.
     *
     * @param message Message to be printed.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }


    /**
     * Prints the welcome message to the console.
     */
    public void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");
    }


    /**
     * Prints the invalid command message to the console.
     */
    public void printInvalidCommandMessage() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
    }


    /**
     * Prints the exit message to the console.
     */
    public void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }













    /**
     * Returns the welcome message.
     * 
     * @return Welcome message.
     */
    public static String getWelcomeMessage() {
        String firstLine = "Hello from Duke!\n";
        String secondLine = "What can I do for you?\n";

        return String.format("%s%s", firstLine, secondLine);
    }


    /**
     * Returns the invalid command message.
     * 
     * @return Invalid command message.
     */
    public String getInvalidCommandMessage() {
        return "OOPS!!! I'm sorry, but I don't know what that means :-(\n";
    }


    /**
     * Returns the exit message.
     * 
     * @return Exit message.
     */
    public String getExitMessage() {
        return "Bye. Hope to see you again soon!\n";
    }

}
