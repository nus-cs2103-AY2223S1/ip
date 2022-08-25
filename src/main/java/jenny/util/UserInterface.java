package jenny.util;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles interaction with the user.
 * CS2103 Week 3
 * AY21/22 Semester 1
 *
 * @author Deon
 */

public final class UserInterface {
    private static final Scanner scanner = new Scanner(System.in);

    public static void greet() {
        print(new String[]{
                "Hello! I'm JennyBot",
                "What can I do for you?"
        });
    }

    public static String read() {
        return (scanner.hasNextLine() ? scanner.nextLine() : "");
    }

    public static void exit() {
        print("Bye. Hope to see you again soon!");
    }

    public static void print(String message) {
        Printer.print(new String[]{message});
    }

    public static void print(String[] messages) {
        Printer.print(messages);
    }

    public static void print(ArrayList<String> messages) {
        Printer.print(messages);
    }
}
