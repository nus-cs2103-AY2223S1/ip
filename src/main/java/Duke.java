import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Simple CLI chatbot that reacts on user input.
 */
public class Duke {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final Scanner scanner = new Scanner(System.in);
    private static final Log log = new Log();
    private static final int INVALID_INDEX = -1;

    /**
     * Main function for the chatbot.
     * 
     * @param args System arguments. Not used for this program.
     */
    public static void main(String[] args) {
        System.out.println("Hello from\n" + LOGO);
        greetingMessage();
        listenForInputs();
    }

    private static void greetingMessage() {
        String[] messages = { "Hello! I'm Duke.", "What can I do for you?" };
        formatAndPrint(List.<String>of(messages));
    }

    private static void formatAndPrint(List<? extends String> texts) {
        String divider = "    ____________________________________________________________";
        String padding = "     ";
        System.out.println(divider);
        texts.forEach((text) -> System.out.println(padding + text));
        System.out.println(divider);
    }

    private static void addToLogAndPrint(String text) {
        log.add(text);
        formatAndPrint(List.of(text));
    }

    private static void listenForInputs() {
        String input = scanner.nextLine();
        if (input.equals("bye")) {
            formatAndPrint(List.<String>of("Bye bye"));
            return;
        } else if (input.equals("list")) {
            formatAndPrint(log.getLogs());
        } else if (input.startsWith("mark") || input.startsWith("unmark")) {
            handleMarkUnmark(input);
        } else {
            addToLogAndPrint(input);
        }
        listenForInputs();
    }

    private static void handleMarkUnmark(String input) {
        int displayIndex = getMarkIndex(input);
        int taskIndex = displayIndex - 1;
        List<String> toPrint = new ArrayList<>();
        if (taskIndex <= INVALID_INDEX || taskIndex >= log.size()) {
            toPrint.add("Invalid index for marking/unmarking!");
        } else if (input.startsWith("mark")) {
            log.markTask(taskIndex);
            toPrint.add("I have marked this task as done: ");
            toPrint.add(log.getTask(taskIndex).toString());
        } else {
            log.unmarkTask(taskIndex);
            toPrint.add("I have unmarked this task: ");
            toPrint.add(log.getTask(taskIndex).toString());
        }
        formatAndPrint(toPrint);
    }

    /**
     * 
     * @param input Input line by user.
     * @return Integer value of second word in line. -1 if not an integer.
     */
    private static int getMarkIndex(String input) {
        String[] splitted = input.split(" ");
        int validArgumentCount = 2;
        if (splitted.length != validArgumentCount) {
            return INVALID_INDEX;
        }
        String secondWord = splitted[1];
        try {
            return Integer.parseInt(secondWord);
        } catch (NumberFormatException e) {
            return INVALID_INDEX;
        }
    }
}
