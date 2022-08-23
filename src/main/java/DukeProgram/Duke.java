package DukeProgram;

import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

public class Duke {
    private static final String DECORATOR = "\t" + "-".repeat(50);
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Job> toDoList = new ArrayList<Job>();
    private static String userName;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        if (SaveManager.deserialize()) {
            loadCurrentUser();
        } else {
            beginNewUser();
        }

        requestCommands();

        printInStyle("Goodbye! Hope to see you again soon!");

        try {
            SaveManager.serialize();
        } catch (IOException e) {
            printInStyle(e.getMessage());
        }
    }

    private static void requestCommands() {
        while (true) {
            switch (askForInput("What would you like to do?")) {
            case "checklist":
                Checklist.use();
                break;

            case "factory reset":
                factoryReset();
                break;

            case "exit":
                return;
            }
        }
    }


    private static void factoryReset() {
        printInStyle(
                String.format("If you are sure you want to do this, " +
                        "%s, please input the following numbers...", userName));

        int key = new Random()
                .nextInt((50000 - 10000) + 1) + 10000;
        try {
            if (key == Integer.parseInt(askForInput(String.format("Type in %d", key)))) {
                printInStyle(String.format("Goodbye %s", userName));
                if (SaveManager.wipeData()) {
                    System.exit(0);
                } else {
                    printInStyle("I couldn't delete the file.");
                }
            } else {
                printInStyle("Terminating last command...");
            }
        } catch (NumberFormatException e) {
            printInStyle("Terminating last command...");
        }
    }

    private static void beginNewUser() {
        printInStyle("This is the first time we've met!");
        userName = askForInput("Why don't you start by introducing yourself, what is your name?");
        printInStyle(String.format("Nice to meet you %s", userName));
        SaveManager.save("userName", userName);
    }

    private static void loadCurrentUser() {
        try {
            userName = SaveManager.load("userName");
            printInStyle(String.format("Welcome back %s", userName));
        } catch (KeyNotFoundException e) {
            printInStyle(e.getMessage());
        }
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
            System.out.println("\t\t" + item.toString());
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

    /**
     * Requests user for input using the scanner
     * @param prompt the prompt to show the user
     * @return the given input from the user
     */
    public static String askForInput(String prompt, boolean noLinebreak) {
        if (!noLinebreak) {
            return askForInput(prompt);
        }

        System.out.print(prompt + " ");
        if (scanner.hasNext()) {
            return scanner.next();
        } else {
            return "bye";
        }
    }
}
