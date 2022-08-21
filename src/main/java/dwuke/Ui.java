package dwuke;

import java.util.Scanner;

/**
 * The user interface of Dwuke.
 */
public class Ui {
    private static final int lineLength = 60;
    private Scanner sc;

    Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns the input entered by the user.
     *
     * @return The user input as a String.
     */
    public String getUserInput() {
        return sc.nextLine();
    }

    /**
     * Prints a long straight line.
     */
    public void showLine() {
        String line = "-".repeat(lineLength);
        System.out.println(line);
    }

    /**
     * Prints the given text for the user.
     *
     * @param text The text to print.
     */
    public void echo(String text) {
        String indentedText = text.replaceAll("(?m)^", "\t");

        this.showLine();
        System.out.println(indentedText);
        this.showLine();
    }

    /**
     * Prints a welcome message for the user.
     */
    public void showWelcomeMessage() {
        String welcomeText = "hewwo UwU! am dwuke\nwat can me do fow u?";
        String indentedText = welcomeText.replaceAll("(?m)^", "\t");

        this.showLine();
        System.out.println(indentedText);
        this.showLine();
    }

    /**
     * Prints a goodbye message for the user.
     */
    public void showGoodbyeMessage() {
        String goodbyeText = "bwye. am hope to UwU u soon";
        String indentedText = goodbyeText.replaceAll("(?m)^", "\t");

        this.showLine();
        System.out.println(indentedText);
        this.showLine();
    }

    /**
     * Prints the error message from the given Exception.
     *
     * @param e The Exception which message to print.
     */
    public void showErrorMessage(Exception e) {
        String errorText = e.getMessage();
        String indentedText = errorText.replaceAll("(?m)^", "\t");

        this.showLine();
        System.out.println(indentedText);
        this.showLine();
    }

    /**
     * Prints the Duke logo.
     */
    public void showLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
    }
}
