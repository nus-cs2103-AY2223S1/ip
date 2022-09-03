package Duke.UI;


import java.util.Scanner;
import java.util.stream.StreamSupport;

/**
 * Command Line Interface(CLI)
 *
 * Read the inputs from users and pass the input
 * Get the result and Output to users
 */
public class TextUI {

    private static final String LINE = "____";
    private Scanner sc = new Scanner(System.in);

    public String readInput() {
        String input;
        do {
            input = sc.nextLine().strip();
        } while (shouldIgnore(input));

        return input;

    }

    private boolean shouldIgnore(String input) {
        if (input.trim().isEmpty()) {
            System.out.println("Empty input");
            return true;

        }
        return false;
    }


}
