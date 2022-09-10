package duke;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    private Scanner s;

    /**
     * Starts accepting input form the user.
     */
    public void start() {
        // Creating the scanner to get input
        this.s = new Scanner(System.in);
    }

    /**
     * Passes the user input to Duke.
     *
     * @return String representing user input.
     */
    public String getInput() {
        return s.nextLine();
    }

    /**
     * Stops receiving user inputs.
     */
    public void end() {
        s.close();
    }

    /**
     * Prints out Duke's greeting to the user.
     */
    public void greet() {
        String logo = " ____                 \n"
                + "|  _ \\ _ _ _ __ _____ \n"
                + "| | | |  _  | |/ / _ \\\n"
                + "| |_| | |_| |   /  __/\n"
                + "|____/ \\__,_|\\_/ \\___|\n";
        System.out.println("Hello! I'm\n" + logo);
        System.out.println("What can I do for you?");
        Duke.printLine();
    }

}
