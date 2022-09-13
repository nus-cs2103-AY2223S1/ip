package hazell.ui;

import hazell.Command;

import java.util.Scanner;

/**
 * A class that abstracts the interactions with user.
 */
public class Cli implements UiInterface {
    Scanner scanner;

    private static final String APP_LOGO = "  _    _               _ _ \n"
            + " | |  | |             | | |\n"
            + " | |__| | __ _ _______| | |\n"
            + " |  __  |/ _` |_  / _ \\ | |\n"
            + " | |  | | (_| |/ /  __/ | |\n"
            + " |_|  |_|\\__,_/___\\___|_|_|\n";


    /**
     * Create a new Ui instance.
     */
    public Cli() {
        scanner = new Scanner(System.in);
    }

    /**
     * Checks if user has entered a new command.
     *
     * @return Whether user has entered a new command
     */
    @Override
    public boolean hasNextUserInput() {
        return scanner.hasNextLine();
    }

    /**
     * Get the next command from user.
     *
     * @return A parsed Command object
     */
    public String getNextUserInput() {
        return scanner.nextLine().strip();
    }

    @Override
    public void displayUserInput(String input) {
        System.out.println(input);
    }


    /**
     * Replies user in a formatted way.
     *
     * @param msg The message to be pretty-printed to user
     */
    @Override
    public void displayBotResponse(String msg) {
        String DIVIDER = "\t____________________________________________________________";
        System.out.println(DIVIDER);
        for (String line : msg.split("\n")) {
            System.out.println("\t" + line);
        }
        System.out.println(DIVIDER);
    }

    public void sendSplash() {
        System.out.println(APP_LOGO);
    }
}
