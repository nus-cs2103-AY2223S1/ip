import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main method of the chatbot, as well as its startup and teardown.
 */
public class Duke {
    /** List of commands */
    private static ArrayList<CommandMatcher> commands;
    /** List of strings to remember */
    private static ArrayList<String> list;

    /**
     * Style and print a single line with a border.
     * @param line line to be printed
     */
    public static void messagePrint(String line) {
        messagePrint(new String[]{line});
    }

    /**
     * Style and print lines with a border.
     * @param lines lines to be printed
     */
    public static void messagePrint(String[] lines) {
        System.out.println(",----------------------------------------------------------------");
        for (String str : lines) {
            System.out.print("| ");
            System.out.println(str);
        }
        System.out.println("'----------------------------------------------------------------");
    }

    private static void greet() {
        String[] greeting = {
            "...where is this again?",
            "Oh, hello, I didn't see you there - I'm Anthea, a chatbot...",
            "...or at least that's what they told me."
        };
        Duke.messagePrint(greeting);
    }

    private static void leave() {
        Duke.messagePrint("It was nice to have you around, I'm going back to sleep...");
    }

    private static void initializeCommands() {
        commands = new ArrayList<>();
        list = new ArrayList<>();

        commands.add(new CommandMatcher((str) -> str.equals("list"), (str) -> {
            String[] output = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                output[i] = (i + 1) + ". " + list.get(i);
            }
            Duke.messagePrint(output);
        }));

        // default command matcher - add to list
        commands.add(new CommandMatcher((str) -> true, (str) -> {
            list.add(str);
            Duke.messagePrint("added: " + str);
        }));
    }

    private static void handleCommand(String command) {
        for (CommandMatcher matcher : commands) {
            if (matcher.run(command)) {
                break;
            }
        }
    }

    /**
     * The main structure of the chatbot execution.
     * @param args command line args which are not used
     */
    public static void main(String[] args) {
        greet();
        initializeCommands();
        Scanner input = new Scanner(System.in);
        boolean keepRunning = true;
        while (keepRunning) {
            String command = input.nextLine();
            if (command.equals("bye")) {
                keepRunning = false;
            } else {
                handleCommand(command);
            }
        }
        leave();
    }
}
