import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static String botName = "Duke";
    private static int lineLength = 80;
    private static ArrayList<String> textList = new ArrayList<>();
    private static boolean isDone = false;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.printf("Hello, I'm %s\n", botName);
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);

        isDone = false;
        while (!isDone) {
            GenerateLine(lineLength);
            String input = scanner.nextLine();
            GenerateLine(lineLength);

            ParseInput(input);
        }

        System.out.println("Goodbye.");
    }

    /**
     * Parses an input string and calls the relevant method (if any)
     * Calls AddToList by default
     *
     * @param input - the input string to be parsed
     */
    public static void ParseInput(String input) {
        switch (input.toLowerCase()) {
            case "bye":
                isDone = true;
                break;
            case "list":
                DisplayList();
                break;
            default:
                AddToList(input);
        }
    }

    /**
     * Prints a line of a specified character length
     *
     * @param length - the character length of the line to print
     */
    public static void GenerateLine(int length) {
        System.out.println(String.format("%" + length + "s", "").replace(" ", "-"));
    }

    /**
     * Adds a text string to the list, which is displayed when DisplayList is called
     *
     * @param text - the text to add to the list
     */
    public static void AddToList(String text) {
        textList.add(text);
        System.out.printf("added: %s\n", text);
    }

    /**
     * Displays the list of texts added to the list so far in order
     */
    public static void DisplayList() {
        for (int i = 0; i < textList.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, textList.get(i));
        }
    }
}
