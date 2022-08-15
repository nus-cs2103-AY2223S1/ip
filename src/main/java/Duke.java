/**
 * This is Duke, he replies to messages.
 * 
 * @author Pei Cheng Yi A0229823Y
 */

// for reading command line inputs
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    /**
     * Greet function writes a greeting to greet the user
     * with the Duke logo appearing at the end
     * 
     * @return void
     */
    private static void greet() {
        System.out.println(Messages.LINE_SEPARATION.message);
        System.out.println(Messages.GREET.message);
        System.out.println(Messages.LOGO.message);
        System.out.println(Messages.LINE_SEPARATION.message);
    }

    /**
     * The exit function prints the exit message to the
     * user
     * 
     * @return void
     */
    private static void exit() {
        System.out.println(Messages.LINE_SEPARATION.message);
        System.out.println(Messages.EXIT.message);
        System.out.println(Messages.LINE_SEPARATION.message);
    }

    /**
     * The echo function prints the input given to it
     * 
     * @param input
     * @return void
     */
    private static void echo(String input) {
        System.out.println(input);
    }

    public static void main(String[] args) throws IOException {
        // Setting up to read command line inputs
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        // Greet the user
        greet();
        // Continue to read inputs until the exit command is entered
        String input = reader.readLine();
        while (!input.equals(Commands.EXIT.command)) {
            echo(input);
            input = reader.readLine();
        }
        // Exit the bot
        exit();
    }
}
