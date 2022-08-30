package duke;

import java.util.Scanner;

/**
 * Class to deal with CLI
 */
class UserInterface {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Print the text to CLI.
     *
     * @param text text to printed.
     */
    public static void print(String text) {
        System.out.println(text);
    }

    /**
     * Get input line from CLI.
     *
     * @return line read from CLI.
     */
    public static String read() {
        return scanner.nextLine();
    }
}
