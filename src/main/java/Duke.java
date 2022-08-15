import java.util.Scanner;

/**
 * Starting point of Duke app.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */

public class Duke {
    public static void main(String[] args) {
        print_line();
        print("Hello! I'm Duke");
        print("What can I do for you?");
        print_line();
        echo();
        exit();
    }

    /**
     * Prints a horizontal line to the console.
     */
    private static void print_line() {
        System.out.print("\t____________________________________________________________\n");
    }

    /**
     * Prints a String to the console.
     * @param printable String to be printed.
     */
    private static void print(String printable) {
        System.out.print("\t" + printable + "\n");
    }

    /**
     * Echos user input to the console.
     * Loops until the keyword is detected.
     */
    private static void echo() {
        Scanner scanner = new Scanner(System.in);
        String arg = scanner.next();
        while(!arg.equals("bye")) {
            print_line();
            print(arg);
            print_line();
            arg = scanner.next();
        }
        scanner.close();
    }

    /**
     * Prints the exit message to the console.
     */
    private static void exit() {
        print_line();
        print("Bye. Hope to see you again soon!");
        print_line();
    }
}
