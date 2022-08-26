package duke;

import java.util.Scanner;

/**
 * A class in charge of interactions with the user
 */
public class Ui {

    private Scanner input = new Scanner(System.in);
    private String response = "";

    /**
     * Constructor for the Ui class
     */
    public Ui() {
    }

    /**
     * Begins the interaction/chat with the user
     */
    public void start() {
        String greetings = "Good day to you! I'm Jake!\n"
                + "I will help you to keep track of your tasks!\n"
                + "The following are your saved tasks:";
        System.out.println(greetings);
    }

    /**
     * Returns the user input stored in the instance field 'response'
     * @return The user input
     */
    public String getResponse() {
        return this.response;
    }

    /**
     * Allow user to type their input
     */
    public void askForInput() {
        this.response = input.nextLine();
    }

    /**
     * Alerts user that given file path does not contain a file
     */
    public void showLoadingError() {
        System.err.println("File path does not contain a file!\n"
                + "But no worries, I have created one for you, add a task now!");
    }
}
