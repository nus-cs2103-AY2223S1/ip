package duke;

import duke.task.TaskList;

import java.util.Scanner;

/**
 * Handles the displaying of the user interface.
 * Most method calls can be chained for a more fluent API.
 */
public class Ui {
    /**
     * Prints a prompt for a command, waiting until the user enters a command.
     * 
     * @return the user's command
     */
    public String readCommand() {
        System.out.print("\n> ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        String logo = " ____        _        \n" +
                "|  _ \\ _   _| | _____ \n" +
                "| | | | | | | |/ / _ \\\n" +
                "| |_| | |_| |   <  __/\n" +
                "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints an error message to the command line.
     * 
     * @param message the error message to print
     * @return this UI object for method chaining
     */
    public Ui showError(String message) {
        System.out.println(message);
        return this;
    }

    /**
     * Prints a normal message to the command line.
     * 
     * @param message the message to print
     * @return this UI object for method chaining
     */
    public Ui showMessage(String message) {
        System.out.println(message);
        return this;
    }

    /**
     * Prints the size of a task list to the command line. 
     * 
     * @param tasks the task list
     * @return this UI object for method chaining
     */
    public Ui showTaskListSize(TaskList tasks) {
        System.out.printf("You now have %d %s.\n",
                tasks.size(),
                tasks.size() == 1 ? "task" : "tasks"
        );
        return this;
    }
}