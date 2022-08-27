package hazell;

import java.util.Scanner;

/**
 * A class that abstracts the interactions with user.
 */
public class Ui {
    Scanner scanner;

    /**
     * Create a new Ui instance.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Check if user has entered a new command.
     * @return Whether user has entered a new command
     */
    public boolean hasNextCommand() {
        return this.scanner.hasNextLine();
    }

    /**
     * Get the next command from user.
     * @return A parsed Command object
     */
    public Command getNextCommand() {
        return Command.parse(this.scanner.nextLine().strip());
    }

    /**
     * Replies user in a formatted way.
     * @param msg The message to be pretty-printed to user
     */
    public void reply(String msg) {
        String DIVIDER = "\t____________________________________________________________";
        System.out.println(DIVIDER);
        for (String line : msg.split("\n")) {
            System.out.println("\t" + line);
        }
        System.out.println(DIVIDER);
    }
}
