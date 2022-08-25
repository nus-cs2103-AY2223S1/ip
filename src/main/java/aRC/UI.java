package arc;

import java.util.Scanner;

/**
 * Encapsulates a UI object that handles user interface
 */
public class UI {
    /**
     * Prints hello message to the screen
     */
    public void hello() {
        System.out.println("Hello! I'm aRC! (°▽°)/\nWhat can I do for you?");
    }

    /**
     * Prints goodbye message to the screen
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon! ʘ ͜ʖ ʘ");
    }

    /**
     * Prints an exception to the screen
     * @param e Exception to be printed
     */
    public void printException(Exception e) {
        e.printStackTrace();
    }

    /**
     * Reads a user input
     * @return The user input
     */
    public String readInput() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n");

        return sc.nextLine();
    }
}
