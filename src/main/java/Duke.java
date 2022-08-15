import java.util.ArrayList;
import java.util.Scanner;

/**
 * Starting point of Jenny chatbot.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */

public class Duke {
    private final ArrayList<String> tasks = new ArrayList<>(100);
    public static void main(String[] args) {
        Duke jenny = new Duke();
        Scanner sc = new Scanner(System.in);
        jenny.greet();
        jenny.add(sc);
        jenny.exit();
    }

    /**
     * Prints a greeting to the console.
     */
    private void greet() {
        Helpers.print_line();
        Helpers.print("Hello! I'm Jenny");
        Helpers.print("What can I do for you?");
        Helpers.print_line();
    }

//    /**
//     * Continuously echos input to the console,
//     * until a terminating keyword is input.
//     */
//    private void echo(Scanner sc) {
//        String text = sc.nextLine();
//        while(!text.equals("bye")) {
//            Helpers.print_line();
//            Helpers.print(text);
//            Helpers.print_line();
//            text = sc.nextLine();
//        }
//    }

    /**
     * Continuously echos input to the console,
     * until a valid command is input,
     * or a terminating command is input.
     */
    private void add(Scanner sc) {
        String text = sc.nextLine();
        while(!text.equals("bye")) {
            if (text.equals("list")) {
                Helpers.print_line();
                int i = 1;
                for (String task : tasks) {
                    Helpers.print(i++ + ". " + task);
                }
                Helpers.print_line();
            } else {
                try {
                    tasks.add(text);
                    Helpers.print_line();
                    Helpers.print("added: " + text);
                    Helpers.print_line();
                } catch (IndexOutOfBoundsException e) {
                    Helpers.print_line();
                    Helpers.print("Error adding " + text);
                    Helpers.print_line();
                }
            }
            text = sc.nextLine();
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
