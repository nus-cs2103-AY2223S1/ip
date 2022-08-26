package dukeprogram.ui;

import dukeprogram.commands.Command;
import dukeprogram.commands.HomepageCommand;
import dukeprogram.parser.Parser;
import exceptions.InvalidCommandException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class UserInterface {
    private static final String DECORATOR = "\t" + "-".repeat(50);
    private static final Scanner scanner = new Scanner(System.in);

    private static final List<String> location = new ArrayList<>();

    public static void requestCommands() {
        boolean canContinue = true;
        while (canContinue) {
            UserInterface.printCurrentLocation();

            try {
                Command command = Parser.parse(
                        new HomepageCommand(),
                        askForInput("[tasks | exit]")
                );

                canContinue = command.execute();
            } catch (InvalidCommandException e) {
                printInStyle(e.getUiMessage().toString());
            }
        }
    }

    public static void advanceLocation(String locationName) {
        location.add(locationName);
    }

    public static void retreatLocation() {
        location.remove(location.size() - 1);
    }

    public static void printCurrentLocation() {
        System.out.println("\t Location | " + String.join(" -> ", location));
    }

    /**
     * Prints the given strings without any formatting, each on a new line
     * @param stringsToPrint the strings to print
     */
    public static void print(String... stringsToPrint) {
        for (String stringToPrint : stringsToPrint) {
            System.out.println(stringToPrint);
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
