package duke.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import duke.commands.CommandResult;

/**
 * UI Class in charge of al things the users see
 */
public class Ui {

    private static final String exitMessage = "Goodbye and have a nice day!";
    private final Scanner scanner;

    /**
     * Ui Constructor
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * The showGreeting function prints the Aladdin Services logo to the console.
     */
    public void showGreeting() {
        File file = new File("src/main/assets/logo.txt");

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Welcome to Aladdin Services");
    }

    /**
     * The showGoodbye function prints a message to the user.
     */
    public void showGoodbye() {
        System.out.println(exitMessage);
    }

    /**
     * The getUserCommand function prompts the user for a command and returns it.
     *
     * @return A string
     */
    public String getUserCommand() {
        System.out.println("Enter Command:");
        return scanner.nextLine();
    }

    /**
     * The showResult function prints the result of a command to the console.
     *
     * @param result
     *            Return the result of the command execution
     */
    public void showResult(CommandResult result) {
        System.out.println(result.getMessage());
    }
}
