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
     * Prints the specified TaskList object to the console.
     * 
     * @param tasks TaskList object to print.
     */
    public void listTasks(TaskList tasks) {
        String result = "Here are the tasks in your list:\n";
        
        for (int i = 0; i < tasks.getSize(); i++) {
            String line = String.format("%d. %s\n", i + 1, tasks.getTask(i));
            result = result.concat(line);
        }
        
        System.out.println(result);
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

}
