package arc;

import java.util.Scanner;

/**
 * Encapsulates a UI object that handles user interface
 */
public class UI {
    /**
     * Prints hello message to the screen
     */
    public static String sayHello() {
        return "Hello! I'm aRC! (°▽°)/\nWhat can I do for you?";
    }

    /**
     * Prints goodbye message to the screen
     */
    public static String sayBye() {
        return "Bye. Hope to see you again soon! ʘ ͜ʖ ʘ";
    }

    /**
     * Prints an exception to the screen
     * @param e Exception to be printed
     */
    public static String getException(DukeException e) {
        return e.getMessage();
    }
}
