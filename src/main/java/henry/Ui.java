package henry;

import java.util.Scanner;

/**
 * Responsible for reading user input.
 */
public class Ui {

    private final Scanner sc;

    /**
     * Instantiates a UI object. When created, the logo will be
     * printed to the console, and the user will be prompted for
     * input.
     */
    public Ui() {
        System.out.println(
            "  _    _ ______ _   _ _______     __\n"
            + " | |  | |  ____| \\ | |  __ \\ \\   / /\n"
            + " | |__| | |__  |  \\| | |__) \\ \\_/ /\n"
            + " |  __  |  __| | . ` |  _  / \\   /\n"
            + " | |  | | |____| |\\  | | \\ \\  | |\n"
            + " |_|  |_|______|_| \\_|_|  \\_\\ |_|");
        sc = new Scanner(System.in);
        output("HELLO. I AM HENRY. HOW MAY I ASSIST YOU TODAY?");
    }

    /**
     * Reads input from the user through Scanner
     *
     * @return the given input from the user as a String
     */
    public String getInput() {
        return sc.nextLine();
    }

    /**
     * Prints the given message in special formatting to the console.
     *
     * @param message the output to be printed to the console
     */
    public void output(String message) {
        System.out.println(formatResponse(message));
    }

    /**
     * Closes the UI
     */
    public void close() {
        output("GOODBYE!");
    }

    private String formatResponse(String input) {
        return "____________________________________________________________" + "\n HENRY: "
               + input + "\n" + "____________________________________________________________";
    }
}
