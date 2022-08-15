import java.util.Scanner;

/**
 * Starting point of Jenny chatbot.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */

public class Duke {
    public static void main(String[] args) {
        Duke jenny = new Duke();
        Scanner sc = new Scanner(System.in);
        jenny.greet();
        jenny.echo(sc);
        jenny.exit();
    }

    /**
     * Prints a greeting to the console.
     */
    public void greet() {
        Helpers.print_line();
        Helpers.print("Hello! I'm Jenny");
        Helpers.print("What can I do for you?");
        Helpers.print_line();
    }

    /**
     * Continuously echos input to the console,
     * until a terminating keyword is input.
     */
    private void echo(Scanner sc) {
        String text = sc.next();
        while(!text.equals("bye")) {
            Helpers.print_line();
            Helpers.print(text);
            Helpers.print_line();
            text = sc.next();
        }
    }

    /**
     * Prints an exit message to the console.
     */
    private void exit() {
        Helpers.print_line();
        Helpers.print("Bye. Hope to see you again soon!");
        Helpers.print_line();
    }
}
