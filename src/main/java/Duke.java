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
        DukePrinter.print_line();
        DukePrinter.print("Hello! I'm Jenny");
        DukePrinter.print("What can I do for you?");
        DukePrinter.print_line();
    }

//    /**
//     * Continuously echos input to the console,
//     * until a terminating keyword is input.
//     */
//    private void echo(Scanner sc) {
//        String text = sc.nextLine();
//        while(!text.equals("bye")) {
//            DukePrinter.print_line();
//            DukePrinter.print(text);
//            DukePrinter.print_line();
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
                DukePrinter.print_line();
                int i = 1;
                for (String task : tasks) {
                    DukePrinter.print(i++ + ". " + task);
                }
                DukePrinter.print_line();
            } else {
                try {
                    tasks.add(text);
                    DukePrinter.print_line();
                    DukePrinter.print("added: " + text);
                    DukePrinter.print_line();
                } catch (IndexOutOfBoundsException e) {
                    DukePrinter.print_line();
                    DukePrinter.print("Error adding " + text);
                    DukePrinter.print_line();
                }
            }
            text = sc.nextLine();
        }
    }

    /**
     * Prints an exit message to the console.
     */
    private void exit() {
        DukePrinter.print_line();
        DukePrinter.print("Bye. Hope to see you again soon!");
        DukePrinter.print_line();
    }
}
