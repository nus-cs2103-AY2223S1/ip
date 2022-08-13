import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /** List of commands */
    private static ArrayList<CommandMatcher> commands;

    /**
     * Style a single line
     * @param line what is to be printed
     */
    public static void messagePrint(String line) {
        messagePrint(new String[]{line});
    }

    /**
     * Style some lines, given as an array of lines
     * @param lines the lines of what is to be printed
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
        Duke.messagePrint(new String[]{
                "...where is this again?",
                "Oh, hello, I didn't see you there - I'm Anthea, a chatbot...",
                "...or at least that's what they told me." });
    }

    private static void leave() {
        Duke.messagePrint("It was nice to have you around, I'm going back to sleep...");
    }

    private static void initializeCommands() {
        commands = new ArrayList<>();

        // default command matcher - echo
        commands.add(new CommandMatcher(
                (str) -> true,
                (str) -> Duke.messagePrint(str)));
    }

    private static void handleCommand(String command) {
        for (CommandMatcher matcher : commands) {
            if (matcher.run(command)) {
                break;
            }
        }
    }

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
