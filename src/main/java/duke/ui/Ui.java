package duke.ui;

import java.util.Scanner;

/**
 * User Interface for the program.
 */
public class Ui {
    private String userInput;
    private Scanner scanner;

    /**
     * Constructor for a user interface.
     * @param scanner
     */
    public Ui(Scanner scanner) {
        this.scanner = scanner;

    }

    /**
     * Prints greeting message.
     */
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello I'm Duke\n What can I do for you?");
    }

    /**
     * Takes in user input and saves it.
     * @return User Input String.
     */
    public String receiveInput() {
        String input = this.scanner.nextLine();
        this.userInput = input;
        return input;
    }

    /**
     * Returns the user input.
     * @return user input.
     */
    public String userString() {
        return this.userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

}
