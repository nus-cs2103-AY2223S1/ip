import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

/**
 * The main method of the chatbot, as well as its startup and teardown.
 */
public class Duke {
    /** List of commands */
    private static ArrayList<CommandMatcher> commands;
    /** List of strings to remember */
    private static ArrayList<Task> list;

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

    private static Optional<Task> getTask(String index) {
        try {
            int idx = Integer.parseInt(index);
            Task task = list.get(idx - 1);
            return Optional.of(task);
        } catch (NumberFormatException ex) {
            messagePrint("Sorry, I didn't understand " + index + ", please give me a number.");
            return Optional.empty();
        } catch (IndexOutOfBoundsException ex) {
            messagePrint("Sorry, the number " + index + ", wasn't in the range.");
            return Optional.empty();
        }
    }

    private static void initializeCommands() {
        commands = new ArrayList<>();
        list = new ArrayList<>();

        commands.add(new CommandMatcher((str) -> str.equals("list"), (str) -> {
            String[] output = new String[list.size() + 1];
            output[0] = "Here, your tasks:";
            for (int i = 0; i < list.size(); i++) {
                output[i + 1] = (i + 1) + "." + list.get(i).toString();
            }
            Duke.messagePrint(output);
        }));

        commands.add(new CommandMatcher("mark ", (str) -> {
            String[] parts = str.split(" ", 2);
            getTask(parts[1]).ifPresent((task) -> {
                task.markAsDone();
                String[] output = {
                    "Marked your task as done:",
                    task.toString()
                };
                Duke.messagePrint(output);
            });
        }));

        commands.add(new CommandMatcher("unmark ", (str) -> {
            String[] parts = str.split(" ", 2);
            getTask(parts[1]).ifPresent((task) -> {
                task.markAsNotDone();
                String[] output = {
                    "Aw... it's not done yet:",
                    task.toString()
                };
                Duke.messagePrint(output);
            });
        }));

        // default command matcher - add to list
        commands.add(new CommandMatcher((str) -> true, (str) -> {
            list.add(new Task(str));
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
