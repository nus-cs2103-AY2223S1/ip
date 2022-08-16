import java.util.ArrayList;
import java.util.Scanner;

/**
 * Starting point of Duke chatbot.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */

public class Duke {
    private static final ArrayList<String> tasks = new ArrayList<>(100);
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greet();
        add(sc);
        exit();
    }

    /**
     * Prints a greeting to the console.
     */
    private static void greet() {
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
    private static void add(Scanner sc) {
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
    private static void exit() {
        DukePrinter.print_line();
        DukePrinter.print("Bye. Hope to see you again soon!");
        DukePrinter.print_line();
    }
}
