package DukeProgram;

import java.util.*;
import java.util.stream.Stream;

public class Duke {
    private static final String DECORATOR = "\t" + "-".repeat(50);

    private static final Scanner scanner = new Scanner(System.in);

    private static final List<Job> toDoList = new ArrayList<Job>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Checklist.Use();

        printInStyle("Goodbye! Hope to see you again soon!");
    }

    /**
     * Prints the given strings in a fancy format
     * @param stringsToPrint the strings to print
     */
    public static void printInStyle(String... stringsToPrint) {
        System.out.println(DECORATOR);
        for (String string : stringsToPrint) {
            System.out.println("\t\t" + string);
        }
        System.out.println(DECORATOR);
    }

    /**
     * Prints the given collection of items and strings in a fancy format
     * @param itemsToPrint the collection of items to print
     * @param others the strings to print
     */
    public static void printInStyle(Iterable<?> itemsToPrint, String... others) {
        System.out.println(DECORATOR);
        for (Object item : itemsToPrint) {
            System.out.println(DECORATOR);
        }

        for (String string : others) {
            System.out.println("\t\t" + string);
        }
        System.out.println(DECORATOR);
    }

    /**
     * Prints the given collection of items and strings in a fancy format
     * @param itemsToPrint the collection of items to print
     * @param others the strings to print
     */
    public static void printInStyle(Stream<?> itemsToPrint, String... others) {
        System.out.println(DECORATOR);
        itemsToPrint.forEach(item -> System.out.println("\t\t" + item.toString()));

        for (String string : others) {
            System.out.println("\t\t" + string);
        }
        System.out.println(DECORATOR);
    }


    /**
     * Requests user for input using the scanner
     * @param prompt the prompt to show the user
     * @return the given input from the user
     */
    public static String askForInput(String prompt) {
        System.out.print(prompt + " ");
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        } else {
            return "bye";
        }
    }
}
