package duke.util;

import java.util.Scanner;

public class UI {
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREET_MESSAGE = "Hello! I am Duke. How can I help you?";

    public static void greet() {
        System.out.println(LOGO);
        print(GREET_MESSAGE);
    }

    public static void print(String message) {
        System.out.println(">> " + message);
    }

    public static String read(Scanner scanner) {
        System.out.print("<< ");
        return scanner.nextLine();
    }
}
