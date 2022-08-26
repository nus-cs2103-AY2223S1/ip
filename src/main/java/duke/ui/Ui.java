package duke.ui;

import java.util.Scanner;

/**
 * Ui class that deals with interactions with the user
 */
public class Ui {

    private static Scanner scanner = new Scanner(System.in);

    public Ui() {
    }

    /**
     * Displays welcome message
     */
    public void showWelcome() {
        System.out.println("Hi... I'm Bishop... \nWhat can I do for you today?");
    }

    /**
     * Reads standard input given by user
     *
     * @return Command inputted by user
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("_____________________________");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showLoadingError() {
        System.out.println("Tasks could not be loaded in");
    }
}
